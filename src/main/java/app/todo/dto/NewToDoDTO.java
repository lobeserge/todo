package app.todo.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class NewToDoDTO {

    @Size(max = 50)
    private String todo_name;

    @Size(max = 100)
    private String todo_description;

    private LocalDate todo_due_date;

    private Long user_id;
}
