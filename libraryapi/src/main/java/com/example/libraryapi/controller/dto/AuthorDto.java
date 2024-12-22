package com.example.libraryapi.controller.dto;

import com.example.libraryapi.model.Author;
import java.time.LocalDate;
import java.util.UUID;

public record AuthorDto(

        UUID id,
        String name,
        LocalDate birthDate,
        String nationality) {


    public Author mapToAuthor(){

        Author author = new Author();
        author.setName(this.name);
        author.setBirthDate(this.birthDate);
        author.setNationality(this.nationality);

        return author;

    }
}
