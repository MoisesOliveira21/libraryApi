package com.example.arquiteturaspring.todos;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }


    @PostMapping
    public TodoEntity save(@RequestBody TodoEntity todo) {
        try{
            return this.service.saveEntity(todo);
        }catch (IllegalArgumentException e){
            var ErrorMessage = e.getMessage();
            throw new ResponseStatusException(HttpStatus.CONFLICT, ErrorMessage);
        }
    }


    @PutMapping("{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody TodoEntity todo) {
        service.updateStatus(todo);
    }

    @GetMapping({"id"})
    public TodoEntity find(@PathVariable("id") Integer id) {
        return service.findById1(id);

    }
}
