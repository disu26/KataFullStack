package co.com.sofka.back_kata_final.controller;

import co.com.sofka.back_kata_final.domain.Todo;
import co.com.sofka.back_kata_final.dto.TodoDto;
import co.com.sofka.back_kata_final.service.TodoService;
import co.com.sofka.back_kata_final.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/todo")
public final class TodoController {

    @Autowired
    private TodoService service;

    private Response response;

    private HttpStatus httpStatus = HttpStatus.OK;

    @GetMapping()
    public List<TodoDto> list(){
        return service.list();
    }

    @PostMapping()
    public TodoDto save(@RequestBody TodoDto todo){
        return service.save(todo);
    }

    @PutMapping()
    public TodoDto update(@RequestBody TodoDto todo){
        if(todo.getId() != null){
            return service.save(todo);
        }
        throw new RuntimeException("No existe el id para actualizar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public Todo get(@PathVariable("id") Long id){
        return service.get(id);
    }

}
