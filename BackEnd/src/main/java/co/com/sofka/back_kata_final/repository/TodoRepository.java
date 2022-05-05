package co.com.sofka.back_kata_final.repository;

import co.com.sofka.back_kata_final.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
