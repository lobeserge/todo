package app.todo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTodoDueDateDTO {
    private Long todo_id;
    private LocalDate todo_due_date;
}
