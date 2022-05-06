package co.com.sofka.back_kata_final.controller;

import co.com.sofka.back_kata_final.domain.TodoList;
import co.com.sofka.back_kata_final.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public final class TodoListController {

    @Autowired
    private TodoListService service;

    @GetMapping(value = "api/todoList")
    public Iterable<TodoList> list(){
        return service.list();
    }

    @PostMapping(value = "api/todoList")
    public TodoList save(@RequestBody TodoList list){
        return service.save(list);
    }

    @PutMapping(value = "api/todoList")
    public TodoList update(@RequestBody TodoList list){
        if(list.getId() != null){
            return service.save(list);
        }
        throw new RuntimeException("No existe el id para actualizar");
    }

    @DeleteMapping(value = "api/{id}/todoList")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    @GetMapping(value = "api/{id}/todoList")
    public TodoList get(@PathVariable("id") Long id){
        return service.getListById(id);
    }
}
