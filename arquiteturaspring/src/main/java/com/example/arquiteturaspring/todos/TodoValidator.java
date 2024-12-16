package com.example.arquiteturaspring.todos;


import org.springframework.stereotype.Component;

@Component
public class TodoValidator {


    private TodoRepository repository;

    public TodoValidator(TodoRepository repository) {
        this.repository = repository;
    }

    public void validate(TodoEntity todo){
        if(repository.existsByDescription(todo.getDescription())){
             throw new IllegalArgumentException("Já existe um TODO ccom esta descricao");
        }

    }
}
