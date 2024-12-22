package com.example.libraryapi.service;

import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class AuthorService {




  private final AuthorRepository authorRepository;


  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }


  public Author saveAuthor(Author author){
    return authorRepository.save(author);
  }


  public Optional<Author> obterPorId(UUID id){
    return authorRepository.findById(id);
  }

  public void deletarPorId(UUID id){
     authorRepository.deleteById(id);
  }
}
