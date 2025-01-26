package com.example.libraryapi.service;

import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import com.example.libraryapi.validator.AuthorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthorService {




  private final AuthorRepository authorRepository;
  private final AuthorValidator validator;


  public AuthorService(AuthorRepository authorRepository, AuthorValidator validator) {
    this.authorRepository = authorRepository;
    this.validator = validator;
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

  }}
