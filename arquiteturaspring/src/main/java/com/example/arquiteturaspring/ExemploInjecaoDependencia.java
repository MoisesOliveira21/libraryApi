package com.example.arquiteturaspring;

import com.example.arquiteturaspring.todos.*;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;

public class ExemploInjecaoDependencia {
    public static void main(String[] args) throws Exception {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("url");
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        Connection connection = dataSource.getConnection();


        EntityManager entityManager = null;

     TodoRepository repository = null;   //ew SimpleJpaRepository<TodoEntity, Integer>();
        MailSender mailSender = new MailSender();
        TodoValidator todoValidator = new TodoValidator(repository);
        TodoService service = new TodoService(repository, todoValidator, mailSender);


    }
}
