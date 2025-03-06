package com.example.libraryapi.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "book")
@Data
@ToString(exclude = "author")
public class Book {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "isbn", length = 50, nullable = false)
    private String isbn;
    @Column(name = "title", length = 50, nullable = false)
    private String title;
    @Column(name = "publication_date", length = 50, nullable = false)
    private LocalDate publicationDate;
    @Enumerated(EnumType.STRING)  // Isso armazena o enum como um inteiro (ordinal).
    @Column(name = "genre", length = 50, nullable = false)
    private BookGenre genre;

    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author")
    private Author author;



}
