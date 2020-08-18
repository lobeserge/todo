package app.todo.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UpdateToDoDTO {

    private Long todo_id;

    @Size(max = 50)
    private String todo_name;

    @Size(max = 100)
    private String todo_description;

}
