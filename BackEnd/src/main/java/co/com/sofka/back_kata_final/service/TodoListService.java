package co.com.sofka.back_kata_final.service;

import co.com.sofka.back_kata_final.domain.TodoList;
import co.com.sofka.back_kata_final.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public final class TodoListService {

    @Autowired
    TodoListRepository repository;

    public Iterable<TodoList> list(){
        return repository.findAll();
    }

    public TodoList save(TodoList list){
        return repository.save(list);
    }

    public TodoList getListById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public void delete(Long id){
        repository.delete(getListById(id));
    }

}
