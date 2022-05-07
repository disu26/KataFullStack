package co.com.sofka.back_kata_final.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "todos_kata")
public final class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;

    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TodoList.class, optional = false)
    @JoinColumn(name = "list_id", nullable = false)
    @JsonBackReference
    private TodoList groupList;
}
