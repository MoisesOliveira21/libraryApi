package com.example.libraryapi.controller.dto;

import org.springframework.cglib.core.Local;

public record AuthorDto(
        String name,
        Local birthDate,
        String nationality) {
}
