package com.example.libraryapi.controller;


import com.example.libraryapi.controller.dto.AuthorDto;
import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import com.example.libraryapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {


    @Autowired
    private AuthorRepository authorRepository;
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createAuthor(@RequestBody AuthorDto authorDto) {
        var autorEntidade = authorDto.mapToAuthor();
        service.saveAuthor(autorEntidade);

        // http://localhost:8080/authors/70esas-acmf91-vmztqb-4gz0qa

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();


//        return ResponseEntity.status(201).body(savedAuthor);
        return ResponseEntity.created(location).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<Object> exibirAuthor(@PathVariable("id") String id) {
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = service.obterPorId(idAuthor);

       ;
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            AuthorDto dto = new AuthorDto(author.getId(), author.getName(), author.getBirthDate(), author.getNationality());

            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }


    // indempontente
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id){

        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = service.obterPorId(idAuthor);

        if(authorOptional.isPresent()){

            service.deletarPorId(idAuthor);

            return ResponseEntity.status(204).build();
        }


        return ResponseEntity.notFound().build();



    }
}
