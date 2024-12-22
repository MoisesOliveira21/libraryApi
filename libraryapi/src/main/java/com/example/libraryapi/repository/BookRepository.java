package com.example.libraryapi.repository;

import com.example.libraryapi.model.Author;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    //QUERY METHOD

    List<Book> findByAuthor(Author author);


    List<Book> findByTitle(String title);

    Book findByIsbn(String isbn);

    List<Book> findByTitleAndPrice(String title, BigDecimal price);


    List<Book> findByPublicationDateBetween(LocalDate inicio, LocalDate fim);


    // select b.* from book as b order by b.tittle
    @Query("select b from Book as b order by b.title")
    List<Book> findBookOrderByTitle();


    // select a.* from book b join author a on a.id = b.id_author
    @Query("select a from Book b join b.author a")
    List<Author> listBookByAuthor();

    // select distinct b.* from Book b
    @Query("select distinct b.title from Book b ")
    List<String> listDistinctBookTitles();


    @Query("""
            select b.genre
            from Book b
            join b.author a 
             where a.nationality = 'Brasileira'
             order by b.genre
                        
            """)
    List<String> listGenresByBrazilianAuthors();

    //named parameters
    @Query("select b from Book b where b.genre = :genre order by b.title")
    List<Book> findByGenreNamedParameters(@Param("genre") BookGenre bookGenre);

    @Query("select b from Book b where b.genre = ?1 order by b.title")
    List<Book> findByGenrePositionalParameters(BookGenre bookGenre);
    @Modifying
    @Transactional
    @Query("delete from Book where genre = ?1")
    void deleteByGenre(BookGenre genre);
    @Modifying
    @Transactional
    @Query("update Book b set b.publicationDate = ?1 where b.genre = ?2")
    void updatePublicationDateByGenre(LocalDate newDate, BookGenre genre);


}
