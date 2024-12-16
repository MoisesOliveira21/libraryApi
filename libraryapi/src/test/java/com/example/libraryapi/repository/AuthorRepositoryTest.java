package com.example.libraryapi.repository;


import com.example.libraryapi.model.Author;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.BookGenre;
import com.example.libraryapi.repository.AuthorRepository;
import com.example.libraryapi.repository.BookRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@SpringBootTest
public class AuthorRepositoryTest {


    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;


    @Test
    public void salvarTest() {
        Author author = new Author();
        author.setName("Irlando");
        author.setNationality("Croaciano");
        author.setBirthDate(LocalDate.of(1982, 4, 26));
        var autorSalvo = authorRepository.save(author);

        System.out.println("Author Salvo: " + autorSalvo);

    }


    @Test
    public void atualizarTest() {
        var id = UUID.fromString("341b6528-1e44-450d-92ea-77fd182e3d99");
        Optional<Author> possivelAutor = authorRepository.findById(id);

        if (possivelAutor.isPresent()) {
            Author authorEncontrado = possivelAutor.get();
            System.out.println("Dados autor encontrado: " + authorEncontrado);

            authorEncontrado.setBirthDate(LocalDate.of(2018, 7, 1));
            authorRepository.save(authorEncontrado);
        }
    }

    @Test
    public  void listTest(){
        List<Author> authorList = authorRepository.findAll();
        authorList.forEach(System.out::println);

    }

    @Test
    public void countTest(){
        System.out.println(authorRepository.count());
    }

    @Test
    public void deleteByIdTest(){
        var id = UUID.fromString("341b6528-1e44-450d-92ea-77fd182e3d99");
        authorRepository.deleteById(id);
    }

    @Test
    public void deleteByAuthor(){
        var id = UUID.fromString("341b6528-1e44-450d-92ea-77fd182e3d99");
        var juanzin = authorRepository.findById(id).get();
        authorRepository.delete(juanzin);
    }

    @Test
    void saveAuthorWithBookTest(){
        Author author = new Author();
        author.setName("Manles");
        author.setNationality("Bulgaro");
        author.setBirthDate(LocalDate.of(1982, 4, 26));

        Book book = new Book();
        book.setIsbn("3376-915");
        book.setGenre(BookGenre.BIOGRAPHY);
        book.setPrice(BigDecimal.valueOf(100.6));
        book.setTitle("NIET");
        book.setPublicationDate(LocalDate.of(1599, 10, 12));
        book.setAuthor(author);

        Book bookdois = new Book();
        bookdois.setIsbn("3376-915");
        bookdois.setGenre(BookGenre.BIOGRAPHY);
        bookdois.setPrice(BigDecimal.valueOf(100.6));
        bookdois.setTitle("NIET");
        bookdois.setPublicationDate(LocalDate.of(1599, 10, 12));
        bookdois.setAuthor(author);



        author.setBookList(new ArrayList<>());
        author.getBookList().add(book);

     authorRepository.save(author);

     bookRepository.saveAll(author.getBookList());//se eu tivesse usado cascade, nao precisava usar o saveAll, Ia salvar sozinho
    }


    @Test
    void listBooksByAuthor(){
        var id = UUID.fromString("341b6528-1e44-450d-92ea-77fd182e3d99");
        var author = authorRepository.findById(id).get();


        List<Book> bookList = bookRepository.findByAuthor(author);
        author.setBookList(bookList);


        author.getBookList().forEach(System.out::println);


    }
}
