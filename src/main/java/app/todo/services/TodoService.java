package app.todo.services;

import app.todo.dto.NewToDoDTO;
import app.todo.dto.UpdateToDoDTO;
import app.todo.dto.UpdateTodoDueDateDTO;
import app.todo.dto.UpdateTodoStatus;
import app.todo.model.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TodoService {
     Todo addTodo(NewToDoDTO newToDoDTO);
     void deleteTodo(Long todo_id);
     List<Todo> getAllUserTodoById(Long user_id);
     List<Todo> getAllUserTodoByDate(LocalDate localDate,Long user_id);
     List<Todo> getAllUserTodoByStatus(String todo_status,Long user_id);
     Todo updateTodo(UpdateToDoDTO updateToDoDTO);
     Todo updateTodoDate(UpdateTodoDueDateDTO updateTodoDueDateDTO);
     Todo updateTodoStatus(UpdateTodoStatus updateTodoStatus);

}

