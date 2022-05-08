package co.com.sofka.back_kata_final.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todos")
public final class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean completed;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TodoList.class, optional = false)
    @JoinColumn(name = "list_id")
    @JsonBackReference
    private TodoList groupList;
}
