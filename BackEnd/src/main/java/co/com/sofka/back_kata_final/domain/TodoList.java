package co.com.sofka.back_kata_final.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import java.util.Set;

/**
 * Entidad del TodoList.
 *
 * @version 1.0.0 2022-05-04
 * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todos_list")
public final class TodoList {
    /**
     * Identificador del todoList
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del todoList
     */
    private String name;

    /**
     * Relación de uno a muchos con la entidad todo.
     */
    @OneToMany(
            mappedBy = "groupList",
            targetEntity = Todo.class,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private Set<Todo> todos;

}
