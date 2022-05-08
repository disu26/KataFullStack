package co.com.sofka.back_kata_final.controller;

import co.com.sofka.back_kata_final.domain.Todo;
import co.com.sofka.back_kata_final.dto.TodoDto;
import co.com.sofka.back_kata_final.service.TodoService;
import co.com.sofka.back_kata_final.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000" , methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.PATCH})
public final class TodoController {

    @Autowired
    private TodoService service;

    private Response response = new Response();

    private HttpStatus httpStatus = HttpStatus.OK;

    @GetMapping(value = "api/todo")
    public List<TodoDto> list(){
        return service.list();
    }

    @PostMapping(value = "api/todo")
    public TodoDto save(@RequestBody TodoDto todo){
        return service.save(todo);
    }

    @PutMapping(value = "api/todo")
    public TodoDto update(@RequestBody TodoDto todo){
        if(todo.getId() != null){
            return service.save(todo);
        }
        throw new RuntimeException("No existe el id para actualizar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public ResponseEntity<Response> delete(@PathVariable("id")Long id){
        response.restart();
        try {
            response.data = service.delete(id);
            if(Boolean.TRUE.equals(response.data)){
                response.message = "Todo eliminado correctamente";
                httpStatus = HttpStatus.OK;
            }else {
                response.message = "Todo no encontrado";
                httpStatus = HttpStatus.NOT_FOUND;
            }
        }catch (Exception exception){
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "api/{id}/todo")
    public Todo get(@PathVariable("id") Long id){
        return service.get(id);
    }

    /**
     * Adminnistrador para las excepciones del sistema.
     *
     * @param exception Objeto con la excepción.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception){
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
