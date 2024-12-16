package com.example.libraryapi.service;


import com.example.libraryapi.model.Author;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.BookGenre;
import com.example.libraryapi.repository.AuthorRepository;
import com.example.libraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransactionService {


    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


    @Transactional
    public void execute(){

        Author author = new Author();
        author.setName("Adalberto");
        author.setNationality("Chileno");
        author.setBirthDate(LocalDate.of(1940,10,2));

        authorRepository.save(author);



        Book book = new Book();
        book.setGenre(BookGenre.FICTION);
        book.setIsbn("1010-2020");
        book.setPrice(BigDecimal.valueOf(300));
        book.setTitle("Tanan tanan");
        book.setAuthor(author);

        bookRepository.save(book);

        if(author.getName().equals("Adalberto")){
            throw new RuntimeException("Rollback");
        }


    }


    // eu nao preciso dar save caso o meu método teenha a notação transactional, bookRepository.save no caso
    @Transactional
    public void atualizacaoSemAtualizar(){
        var book = bookRepository.findById(UUID.fromString("jsbvm1aijzlajdnc1ac6bgg6")).orElse(null);

        // A entidade 'book' agora está sob controle do JPA (managed entity)
        book.setPublicationDate(LocalDate.of(1999, 10,6));
        // Não é necessário chamar save() aqui, pois o JPA irá persistir as alterações no commit da transação
    }

}
