package com.example.arquiteturaspring;


import com.example.arquiteturaspring.todos.TodoEntity;
import com.example.arquiteturaspring.todos.TodoService;
import com.example.arquiteturaspring.todos.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component

public class ManagedBean {


    @Autowired
    private TodoValidator validator;


    @Autowired
    public ManagedBean(TodoValidator validator) {
        this.validator = validator;
    }
        public void utilizar() {
        var todo = new TodoEntity();
        validator.validate(todo);
    }
    @Autowired
    public void setValidator(TodoValidator validator) {
        this.validator = validator;
    }

}
