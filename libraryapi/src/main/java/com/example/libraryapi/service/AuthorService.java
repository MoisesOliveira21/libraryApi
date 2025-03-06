package com.example.libraryapi.service;

import com.example.libraryapi.exceptions.OperationNotAllowedException;
import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import com.example.libraryapi.repository.BookRepository;
import com.example.libraryapi.validator.AuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthorService {




  private final AuthorRepository authorRepository;
  private final AuthorValidator validator;
  private final BookRepository bookRepository;


  public AuthorService(AuthorRepository authorRepository, AuthorValidator validator, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.validator = validator;
    this.bookRepository = bookRepository;
  }

  public Author saveAuthor(Author author){


   validator.validar(author);
    return  authorRepository.save(author);
  }


  public void updateAuthorService(Author author){


    if(author.getId() == null){
      throw new IllegalArgumentException("Para atualizar, é necessário que o autor exista");
    }

    validator.validar(author);
    authorRepository.save(author);
  }



  public Optional<Author> obterPorId(UUID id){
    return authorRepository.findById(id);
  }

  public void deletarPorId(UUID id){
     authorRepository.deleteById(id);
  }



  public void deleteAuthor(Author author){

    if(hasBook(author)){
      throw new OperationNotAllowedException("Já possui livro");
    }

    authorRepository.delete(author);
  }

  public List<Author> pesquisae(String name, String nationality){

    if(name != null && nationality != null){

      return authorRepository.findByNameAndNationality(name,nationality);
    }
    if(name != null){
      return authorRepository.findByName(name);
    }

    if(nationality != null){
      return authorRepository.findByNationality(nationality);
    }

    return authorRepository.findAll();

  }


  public List<Author> pesquisaByExample(String name, String nationality){

    var autor = new Author();
    autor.setName(name);
    autor.setNationality(nationality);

    ExampleMatcher matcher  = ExampleMatcher
            .matching()
            .withIgnoreNullValues()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example<Author> authorExample = Example.of(autor, matcher);


    return authorRepository.findAll(authorExample);
  }


  public boolean hasBook(Author author){
    return bookRepository.exitsAuthor(author);
  }
}
