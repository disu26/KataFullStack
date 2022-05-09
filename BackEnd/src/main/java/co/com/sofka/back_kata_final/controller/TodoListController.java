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

import java.util.List;

/**
 * Controlador para el todoList.
 *
 * @version 1.0.0 2022-05-04
 * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
 * @since 1.0.0
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public final class TodoListController {

    /**
     * Servicio para el manejo de los todoList.
     */
    @Autowired
    private TodoListService service;

    /**
     * Devuelve un listado con los todos creados
     *
     * @return List con todos los todos creados
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(value = "api/todoList")
    public List<TodoList> list(){
        return service.list();
    }

    /**
     * Creación de un nuevo todoList.
     *
     * @param list Objeto todoList que se desea almacenar
     * @return Objeto TodoList que fue almacenado
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(value = "api/todoList")
    public TodoList save(@RequestBody TodoList list){
        return service.save(list);
    }

    /**
     * Actualización total de un todoList
     *
     * @param list objeto con la nueva información del todoList
     * @return Objeto TodoList que fue actualizado
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @PutMapping(value = "api/todoList")
    public TodoList update(@RequestBody TodoList list){
        if(list.getId() != null){
            return service.save(list);
        }
        throw new RuntimeException("No existe el id para actualizar");
    }

    /**
     * Eliminación de un todoList.
     *
     * @param id del todoList que se desea eliminar
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(value = "api/{id}/todoList")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    /**
     * Obtener un todoList por su id.
     *
     * @param id del todoList a buscar.
     * @return TodoList encontrado por su id.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(value = "api/{id}/todoList")
    public TodoList get(@PathVariable("id") Long id){
        return service.getListById(id);
    }
}
