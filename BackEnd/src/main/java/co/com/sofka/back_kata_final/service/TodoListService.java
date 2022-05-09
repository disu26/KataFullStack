package co.com.sofka.back_kata_final.service;

import co.com.sofka.back_kata_final.domain.TodoList;
import co.com.sofka.back_kata_final.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase tipo Servicio para el manejo de los todoList.
 *
 * @version 1.0.0 2022-05-04
 * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
 * @since 1.0.0
 */
@Service
public class TodoListService {

    /**
     * Repositorio del todoList.
     */
    @Autowired
    TodoListRepository repository;

    /**
     * Devuelve una lista con todos los todoList.
     *
     * @return lista con todos los todoList.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional(readOnly = true)
    public List<TodoList> list(){
        return repository.findAll();
    }

    /**
     * Guarda un nuevo todoList.
     *
     * @param list que se desea guardar.
     * @return Todolist que fue guardado.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional
    public TodoList save(TodoList list){
        return repository.save(list);
    }

    /**
     * Devuelve una lista en base a su id.
     *
     * @param id de la lista que se desea encontrar.
     * @return Todolist que fue obtenido.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional(readOnly = true)
    public TodoList getListById(Long id){
        return repository.findById(id).orElseThrow();
    }

    /**
     * Borra un todoList con su id.
     *
     * @param id del todoList que se desea borrar
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional
    public void delete(Long id){
        repository.delete(getListById(id));
    }

}
