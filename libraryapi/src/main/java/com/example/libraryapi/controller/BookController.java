package com.example.libraryapi.controller;

import com.example.libraryapi.BookMapper;
import com.example.libraryapi.controller.dto.BookDto;
import com.example.libraryapi.model.Author;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.repository.BookRepository;
import com.example.libraryapi.service.AuthorService;
import com.example.libraryapi.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;
    private final BookRepository repository;
    private final AuthorService authorService;
    private final BookMapper mapper;

    @PostMapping
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto dto) {
        var book = service.save(dto, mapper);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> buscarLivroPorId(@PathVariable UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("nch id" + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarLivro(@PathVariable UUID id, @RequestBody @Valid BookDto dto) {
        Optional<Book> livroExistente = service.findById(id);
        if (livroExistente.isEmpty()) {
            throw new EntityNotFoundException("nch id" + id);
        }

        Optional<Author> author = authorService.obterPorId(dto.authorId());
        if (author.isEmpty()) {
            return ResponseEntity.badRequest().body("n acho");
        }

        Book book = livroExistente.get();
        book.setIsbn(dto.isbn());
        book.setTitle(dto.title());
        book.setPublicationDate(dto.publicationDate());
        book.setGenre(dto.genre());
        book.setPrice(dto.price());
        book.setAuthor(author.get());

        service.save(book);
        return ResponseEntity.ok("livro att");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable UUID id) {
        Optional<Book> livro = service.findById(id);
        if (livro.isEmpty()) {
            throw new EntityNotFoundException("nada" + id);
        }

        service.delete(id);
        return ResponseEntity.ok("deletado...");
    }
}
