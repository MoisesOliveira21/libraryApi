package com.example.libraryapi.validator;


import com.example.libraryapi.exceptions.RegistroDuplicadoException;
import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {

    private AuthorRepository repository;

    public AuthorValidator(AuthorRepository repository) {
        this.repository = repository;
    }

    public void validar(Author author) {
        if (existsAuthor(author)) {
            throw new RegistroDuplicadoException("Author already registed");
        }

    }


    private boolean existsAuthor(Author author) {
        Optional<Author> authorOptional = repository.findByNameAndBirthDateAndNationality(author.getName(),
                author.getBirthDate(), author.getNationality());

        if (author.getId() == null) {
            return authorOptional.isPresent();
        }
        return !author.getId().equals(authorOptional.get().getId());
    }

}
