package co.com.sofka.back_kata_final.service;

import co.com.sofka.back_kata_final.domain.Todo;
import co.com.sofka.back_kata_final.dto.TodoDto;
import co.com.sofka.back_kata_final.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase tipo Servicio para el manejo de los todo.
 *
 * @version 1.0.0 2022-05-04
 * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
 * @since 1.0.0
 */
@Service
public class TodoService {

    /**
     * Repositorio del todo.
     */
    @Autowired
    private TodoRepository repository;

    /**
     * Objeto para mapear el dto.
     */
    @Autowired
    ModelMapper mapper;

    /**
     * Devuelve una lista con todos los todo.
     *
     * @return lista con todos los todo.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional(readOnly = true)
    public List<TodoDto> list(){
        return repository.findAll().stream().map(this::mapTodoDto).toList();
    }

    /**
     * Mapea y guarda el objeto todo.
     *
     * @param todo DTO que se va a almacenar
     * @return Objeto que fue guardado.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional
    public TodoDto save(TodoDto todo){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Todo newTodo = mapper.map(todo, Todo.class);
        Todo returnTodo = repository.save(newTodo);
        return mapper.map(returnTodo, TodoDto.class);
    }

    /**
     * Borra un todo en base a su id.
     *
     * @param id del todo que se desea borrar
     * @return Boolean que indica si el objeto fue borrado o no.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional
    public Boolean delete(Long id){
        var todo = repository.findById(id);
        if(todo.isPresent()){
            repository.delete(todo.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Devuelve un todo en base a su id.
     *
     * @param id del todo que se desa obtener.
     * @return Todo encontrado en base a su id.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    @Transactional(readOnly = true)
    public Todo get(Long id){
        return repository.findById(id).orElseThrow();
    }

    /**
     * Método utilizado para mapear un todo a su dto.
     *
     * @param todo que se desea mapear.
     * @return todoDto mapeado.
     *
     * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
     * @since 1.0.0
     */
    private TodoDto mapTodoDto(Todo todo){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TodoDto todoDto;
        todoDto = mapper.map(todo, TodoDto.class);
        return todoDto;
    }
}
