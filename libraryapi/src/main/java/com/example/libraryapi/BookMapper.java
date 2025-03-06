package com.example.libraryapi;

import com.example.libraryapi.controller.dto.BookDto;
import com.example.libraryapi.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookDto dto);
    BookDto toDto(Book book);

}
