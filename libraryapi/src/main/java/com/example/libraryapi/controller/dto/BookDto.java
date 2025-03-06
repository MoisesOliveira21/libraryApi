package com.example.libraryapi.controller.dto;

import com.example.libraryapi.model.BookGenre;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookDto(UUID id,
                      @NotBlank String isbn,
                      @NotBlank String title,
                      @NotNull LocalDate publicationDate,
                      @NotNull BookGenre genre,
                      @DecimalMin(value = "0.0", inclusive = false) BigDecimal price,
                      @NotNull UUID authorId) {
}
