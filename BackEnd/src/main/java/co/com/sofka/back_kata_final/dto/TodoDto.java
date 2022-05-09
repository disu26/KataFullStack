package co.com.sofka.back_kata_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Objeto DTO de la clase Todo.
 *
 * @author Dímar Andrey Suárez Hidalgo <dimar260212@gmail.com>
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class TodoDto implements Serializable {
    private Long id;
    private String name;
    private Boolean completed;
    private Long groupListId;
    private String groupListName;
}
