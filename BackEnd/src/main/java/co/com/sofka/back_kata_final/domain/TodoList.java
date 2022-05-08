package co.com.sofka.back_kata_final.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todos_list")
public final class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "groupList",
            targetEntity = Todo.class,
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private Set<Todo> todos;

}
