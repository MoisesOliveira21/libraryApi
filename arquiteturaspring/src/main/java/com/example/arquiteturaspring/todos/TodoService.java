package com.example.arquiteturaspring.todos;


import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository repository;
    private TodoValidator validator;
    private MailSender mailSender;

    public TodoService(TodoRepository repository, TodoValidator validator, MailSender mailSender) {
        this.repository = repository;
        this.validator = validator;
        this.mailSender = mailSender;
    }

    public TodoEntity saveEntity(TodoEntity newTodo){

        validator.validate(newTodo);
        return repository.save(newTodo);
    }


    public void updateStatus(TodoEntity todo) {
        repository.save(todo);
        String  completed = Boolean.TRUE.equals(todo.getIsCompleted()) ? "Concluído" : "Não foi concluído";
        mailSender.enviar("Todo " + todo.getDescription() + "foi atualizado para: " + completed);
    }

    public TodoEntity findById1(Integer id){
        return repository.findById(id).orElse(null);
    }
}
