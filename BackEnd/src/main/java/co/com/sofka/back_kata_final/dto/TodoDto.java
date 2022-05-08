package co.com.sofka.back_kata_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
