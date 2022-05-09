package co.com.sofka.back_kata_final.repository;

import co.com.sofka.back_kata_final.domain.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para el TodoList.
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
