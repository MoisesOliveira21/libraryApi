package com.example.libraryapi.controller.dto;

import com.example.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDto(

        UUID id,

        @NotBlank(message = "campo obrigatorio")
        @Size(min = 2, max = 120, message = "campo fora do tamanho padrao")
        String name,

        @NotNull
        @Past(message = "data apenas do passado")
        LocalDate birthDate,

        @NotBlank
        @Size(min = 2, max = 30, message = "campo fora do tamanho padrao")

        String nationality) {


    public Author mapToAuthor() {

        Author author = new Author();
        author.setName(this.name);
        author.setBirthDate(this.birthDate);
        author.setNationality(this.nationality);

        return author;

    }
}
