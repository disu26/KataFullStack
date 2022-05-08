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

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    @Autowired
    ModelMapper mapper;

    @Transactional(readOnly = true)
    public List<TodoDto> list(){
        return repository.findAll().stream().map(this::mapTodoDto).toList();
    }

    @Transactional
    public TodoDto save(TodoDto todo){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Todo newTodo = mapper.map(todo, Todo.class);
        Todo returnTodo = repository.save(newTodo);
        return mapper.map(returnTodo, TodoDto.class);
    }

    @Transactional
    public Boolean delete(Long id){
        var todo = repository.findById(id);
        if(todo.isPresent()){
            repository.delete(todo.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Transactional(readOnly = true)
    public Todo get(Long id){
        return repository.findById(id).orElseThrow();
    }

    private TodoDto mapTodoDto(Todo todo){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TodoDto todoDto;
        todoDto = mapper.map(todo, TodoDto.class);
        return todoDto;
    }
}
