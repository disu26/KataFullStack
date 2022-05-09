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

/**
 * Controlador para el todo.
 *
 * @version 1.0.0 2022-05-04
 * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
 * @since 1.0.0
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000" , methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.PATCH})
public final class TodoController {

    /**
     * Servicio para el manejo del todo.
     */
    @Autowired
    private TodoService service;

    /**
     * Variable para el manejo de las respuestas de las API
     */
    private final Response response = new Response();

    /**
     * Variable para el manejo del código HTTP que se responde en las API
     */
    private HttpStatus httpStatus = HttpStatus.OK;

    /**
     * Devuelve un listado con los todos creados
     *
     * @return List con todos los todos creados
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(value = "api/todo")
    public List<TodoDto> list(){
        return service.list();

    }

    /**
     * Creación de un nuevo todo.
     *
     * @param todo Objeto todo que se desea almacenar
     * @return Objeto TodoDto que fue almacenado
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(value = "api/todo")
    public TodoDto save(@RequestBody TodoDto todo){
        return service.save(todo);
    }

    /**
     * Actualización total de un todo
     *
     * @param todo objeto con la nueva información del todo
     * @return Objeto TodoDto que fue actualizado
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @PutMapping(value = "api/todo")
    public TodoDto update(@RequestBody TodoDto todo){
        if(todo.getId() != null){
            return service.save(todo);
        }
        throw new RuntimeException("No existe el id para actualizar");
    }

    /**
     * Eliminación de un todo.
     *
     * @param id del todo que se desea eliminar
     * @return Objeto Response en formato JSON
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
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

    /**
     * Obtener un todo por su id.
     *
     * @param id del todo a buscar.
     * @return Todo encontrado por su id.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(value = "api/{id}/todo")
    public Todo get(@PathVariable("id") Long id){
        return service.get(id);
    }

    /**
     * Administrador para las excepciones del sistema.
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
