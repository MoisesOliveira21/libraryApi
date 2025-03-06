package com.example.libraryapi.controller;


import com.example.libraryapi.controller.dto.AuthorDto;
import com.example.libraryapi.controller.dto.ResponseError;
import com.example.libraryapi.exceptions.OperationNotAllowedException;
import com.example.libraryapi.exceptions.RegistroDuplicadoException;
import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import com.example.libraryapi.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public ResponseEntity<Object> createAuthor(@RequestBody @Valid AuthorDto authorDto) {
        try {
            Author entityAuthor = authorDto.mapToAuthor();
            service.saveAuthor(entityAuthor);

            //http://localhost:8080/authors/70esas-acmf91-vmztqb-4gz0qa

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(entityAuthor.getId())
                    .toUri();


            return ResponseEntity.created(location).build();

        }catch (RegistroDuplicadoException e){
            var errorDto = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(errorDto.status()).body(errorDto);

        }

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
    public ResponseEntity<Object> deleteAuthor(@PathVariable @Valid String id) {

      try {
          var idAuthor = UUID.fromString(id);
          Optional<Author> authorOptional = service.obterPorId(idAuthor);

          if (authorOptional.isPresent()) {

              service.deletarPorId(idAuthor);

              return ResponseEntity.status(204).build();
          }


          return ResponseEntity.notFound().build();
      }catch (OperationNotAllowedException e){
          var errorDto = ResponseError.padrao(e.getMessage());
          return ResponseEntity.status(errorDto.status()).body(errorDto);
      }


    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> filtrarAuthor(@RequestParam(value = "name", required = false) String name,
                                                         @RequestParam(value = "nationality", required = false) String nationality) {


        List<Author> listAuthor = service.pesquisaByExample(name, nationality);
        List<AuthorDto> listAuthorDto = listAuthor.stream()
                .map(author ->
                        new AuthorDto(author.getId(),
                                author.getName(),
                                author.getBirthDate()
                                , author.getNationality())).collect(Collectors.toList());

        return ResponseEntity.status(200).body(listAuthorDto);


    }


    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody AuthorDto dto) {

          try{

              var idAuthor = UUID.fromString(id);
              Optional<Author> authorOptional = service.obterPorId(idAuthor);

              if(authorOptional.isEmpty()){
                  return ResponseEntity.notFound().build();
              }

              var author = authorOptional.get();
              author.setName(dto.name());
              author.setNationality(dto.nationality());
              author.setBirthDate(dto.birthDate());
              service.updateAuthorService(author);
              return ResponseEntity.noContent().build();
          }catch (RegistroDuplicadoException e){
              var errorDto = ResponseError.conflict(e.getMessage());
              return ResponseEntity.status(errorDto.status()).body(errorDto);
          }



    }
}
