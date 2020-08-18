package app.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    private String todo_name;

    @Size(max = 100)
    private String todo_description;

    private LocalDate todo_creation_date;

    private LocalDate todo_due_date;

    private String todo_status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "todo_id", nullable = false)
    private User user;
}
