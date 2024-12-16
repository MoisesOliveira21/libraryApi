package com.example.libraryapi.repository;

import com.example.libraryapi.model.Author;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.BookGenre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;


    @Test
    void saveTest() {
        Book book = new Book();
        book.setIsbn("254-221");
        book.setGenre(BookGenre.FICTION);
        book.setPrice(BigDecimal.valueOf(50.4));
        book.setTitle("Harryson");
        book.setPublicationDate(LocalDate.of(1999, 4, 25));

        Author author = authorRepository.findById(UUID.fromString("341b6528-1e44-450d-92ea-77fd182e3d99")).orElse(null);
        book.setAuthor(author);
        bookRepository.save(book);


    }

    @Test
    void saveCascadeTest() {
        Book book = new Book();
        book.setIsbn("2524-441");
        book.setGenre(BookGenre.ROMANCE);
        book.setPrice(BigDecimal.valueOf(50.4));
        book.setTitle("BLUGSON");
        book.setPublicationDate(LocalDate.of(1299, 8, 21));

        Author author = new Author();
        author.setName("Inston");
        author.setNationality("Australinson");
        author.setBirthDate(LocalDate.of(1911, 5, 16));


        book.setAuthor(author);
//aq com cascade eu não preciso salvar o autor no repository
        bookRepository.save(book);

    }


    @Test
    void saveAuthorAndBookTest() {
        Book book = new Book();
        book.setIsbn("2524-441");
        book.setGenre(BookGenre.ROMANCE);
        book.setPrice(BigDecimal.valueOf(50.4));
        book.setTitle("BLUGSON");
        book.setPublicationDate(LocalDate.of(1299, 8, 21));

        Author author = new Author();
        author.setName("Rerson");
        author.setNationality("Islamison");
        author.setBirthDate(LocalDate.of(2011, 8, 1));
        authorRepository.save(author);

        book.setAuthor(author);
        bookRepository.save(book);

    }


    @Test
    void updateBookTest() {

        UUID id = UUID.fromString("adsdd");
        var bookToUpdate = bookRepository.findById(id).orElse(null);

        UUID idAuthor = UUID.fromString("aaaaa");
        Author renan = authorRepository.findById(idAuthor).orElse(null);


        bookToUpdate.setAuthor(renan);
        bookRepository.save(bookToUpdate);
    }

    @Test
    void deleteCascadeBookTest() {
        UUID id = UUID.fromString("adsdd");
        bookRepository.deleteById(id);
    }


    @Test
    @Transactional
// tem que usar do springframework, o do jakarta nao funciona, o transact permite eu acessar os dados do autor
        // mesmo com lazy que n permite
    void searchBookTest() {
        UUID id = UUID.fromString("daed83b3-65fd-49eb-9400-cbc0af13059d");
        Book book = bookRepository.findById(id).orElse(null);
        System.out.println("Book:");
        System.out.println(book.getTitle());
//    System.out.println("Author:");
//    System.out.println(book.getAuthor().getName());
    }
    @Test
    void searchByTitleTest() {
        List<Book> bookList = bookRepository.findByTitle("tanana tanana");
        bookList.forEach(System.out::println);
    }

    @Test
    void searchByIsbnTest() {
        Book book = bookRepository.findByIsbn("31110-555");
        System.out.println(book);
        var price = BigDecimal.valueOf(200.00);
        var title = "aaa";
        bookRepository.findByTitleAndPrice(title,price);
    }


    @Test
    void listBookWithQueryJPQL(){
        var result = bookRepository.findBookOrderByTitle();
        result.forEach(System.out::println);
    }
    @Test
    void listBookByAuthorWithQueryJPQL(){
        var result = bookRepository.listBookByAuthor();
        result.forEach(System.out::println);
    }

    @Test
    void listBookDistinctWithQueryJPQL(){
        var result = bookRepository.listDistinctBookTitles();
        result.forEach(System.out::println);
    }
    @Test
    void listBookGenreByBrazilianAuthorsWithQueryJPQL(){
        var result = bookRepository.listGenresByBrazilianAuthors();
        result.forEach(System.out::println);
    }

    @Test
    void listBookByGenreOrderedWithQueryJPQL(){
        var result = bookRepository.findByGenreNamedParameters(BookGenre.FANTASY, "publicationDate");
        result.forEach(System.out::println);
    }

    @Test
    void listBookByGenreOrderedByPositionalParamWithQueryJPQL(){
        var result = bookRepository.findByGenrePositionalParameters(BookGenre.FANTASY, "publicationDate");
        result.forEach(System.out::println);
    }



    @Test
    void deleteByGenreTest(){
        bookRepository.deleteByGenre(BookGenre.FICTION);
    }


    @Test
    void updatePublicationData(){

       var newDate = LocalDate.of(1999, 05, 15);
        bookRepository.updatePublicationDateByGenre(newDate, BookGenre.BIOGRAPHY);
    }



}