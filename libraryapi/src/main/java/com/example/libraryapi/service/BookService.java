package com.example.libraryapi.service;

import com.example.libraryapi.BookMapper;
import com.example.libraryapi.controller.dto.BookDto;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.repository.AuthorRepository;
import com.example.libraryapi.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Book save(BookDto dto, BookMapper mapper) {
        var author = authorRepository.findById(dto.authorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        var book = mapper.toEntity(dto);
        book.setAuthor(author);

        return save(book);
    }


}
