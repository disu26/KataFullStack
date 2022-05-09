package co.com.sofka.back_kata_final.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

/**
 * Entidad del Todo.
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
@Table(name = "todos")
public final class Todo {
    /**
     * Identificador del todo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del todo.
     */
    private String name;

    /**
     * Boolean que indica si ya fue completado.
     */
    private Boolean completed;

    /**
     * Relación de muchos a uno con la entidad todoList.
     */
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TodoList.class, optional = false)
    @JoinColumn(name = "list_id")
    @JsonBackReference
    private TodoList groupList;
}
