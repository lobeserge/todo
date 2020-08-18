package app.todo.dto;

import lombok.Data;

@Data
public class UpdateTodoStatus {
    private String todo_status;
    private Long todo_id;
}
