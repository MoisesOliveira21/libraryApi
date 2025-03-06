package com.example.libraryapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "author")
@Data
@ToString(exclude = "author")
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Author {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "A nacionalidade não pode estar vazia")
    @Column(name = "nationality", length = 50, nullable = false)
    private String nationality;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Transient
    private List<Book> bookList;




    @CreatedDate
    @Column(name= "created_at")
    private LocalDateTime createAt;


    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updateAt;


    @Column(name= "id_user")
    private UUID idUser;
    @Deprecated
    public Author() {

    }


}
