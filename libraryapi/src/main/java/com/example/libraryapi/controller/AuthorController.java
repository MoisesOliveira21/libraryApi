package com.example.libraryapi.controller;


import com.example.libraryapi.controller.dto.AuthorDto;
import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {



    @Autowired
    AuthorRepository authorRepository;

    @PostMapping
    public ResponseEntity<Object> saveAuthor(@RequestBody Author author){
        Author savedAuthor = authorRepository.save(author);


//        return ResponseEntity.status(201).body(savedAuthor);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);

    }

}
