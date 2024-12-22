package com.example.libraryapi;

import com.example.libraryapi.model.Author;
import com.example.libraryapi.repository.AuthorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryapiApplication {

	public static void main(String[] args) {

		var context = SpringApplication.run(LibraryapiApplication.class, args);
		AuthorRepository repository = context.getBean(AuthorRepository.class);
		exemploAuthor(repository);
	}


	public static void exemploAuthor(AuthorRepository authorRepository){
		Author author = new Author();
		author.setName("Jundai");
		author.setNationality("mexicano");
		author.setBirthDate(LocalDate.of(1969,8, 17));
		var authorSalvo = authorRepository.save(author);
		System.out.println("AutorSalvo " + authorSalvo);
	}

}
