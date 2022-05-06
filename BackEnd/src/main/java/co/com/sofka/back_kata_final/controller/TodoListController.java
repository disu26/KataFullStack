package co.com.sofka.back_kata_final.controller;

import co.com.sofka.back_kata_final.domain.Todo;
import co.com.sofka.back_kata_final.domain.TodoList;
import co.com.sofka.back_kata_final.dto.TodoDto;
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

    @GetMapping(value = "api/todos")
    public Iterable<TodoList> list(){
        return service.list();
    }

    @PostMapping(value = "api/todo")
    public TodoList save(@RequestBody TodoList list){
        return service.save(list);
    }

    @PutMapping(value = "api/todo")
    public TodoList update(@RequestBody TodoList list){
        if(list.getId() != null){
            return service.save(list);
        }
        throw new RuntimeException("No existe el id para actualizar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public TodoList get(@PathVariable("id") Long id){
        return service.getListById(id);
    }
}
