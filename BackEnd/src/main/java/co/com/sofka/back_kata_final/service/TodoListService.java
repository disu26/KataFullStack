package co.com.sofka.back_kata_final.service;

import co.com.sofka.back_kata_final.domain.TodoList;
import co.com.sofka.back_kata_final.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TodoListService {

    @Autowired
    TodoListRepository repository;

    @Transactional(readOnly = true)
    public List<TodoList> list(){
        return repository.findAll();
    }

    @Transactional
    public TodoList save(TodoList list){
        return repository.save(list);
    }

    @Transactional(readOnly = true)
    public TodoList getListById(Long id){
        return repository.findById(id).orElseThrow();
    }

    @Transactional
    public void delete(Long id){
        repository.delete(getListById(id));
    }

}
