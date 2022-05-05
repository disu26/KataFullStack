package co.com.sofka.back_kata_final.service;

import co.com.sofka.back_kata_final.domain.Todo;
import co.com.sofka.back_kata_final.dto.TodoDto;
import co.com.sofka.back_kata_final.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    @Autowired
    ModelMapper mapper;

    public Iterable<Todo> list(){
        return repository.findAll();
    }

    public TodoDto save(TodoDto todo){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Todo newTodo = mapper.map(todo, Todo.class);
        Todo returnTodo = repository.save(newTodo);
        return mapper.map(returnTodo, TodoDto.class);
    }

    public void delete(Long id){
        repository.delete(get(id));
    }

    public Todo get(Long id){
        return repository.findById(id).orElseThrow();
    }
}
