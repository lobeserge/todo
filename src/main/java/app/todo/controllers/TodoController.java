package app.todo.controllers;

import app.todo.dto.NewToDoDTO;
import app.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<?> addTodo(@Valid @RequestBody NewToDoDTO newToDoDTO) {
        return ResponseEntity.ok(todoService.addTodo(newToDoDTO));
    }

    @DeleteMapping("/delete/{todo_id}")
    ResponseEntity<?> deleteTodo(@PathVariable("todo_id") Long  todo_id) {
        todoService.deleteTodo(todo_id);
        return ResponseEntity.ok("todo deleted");
    }

    @GetMapping("/user_id/{user_id}")
    public ResponseEntity<?> getAllUserTodoById(@PathVariable("user_id") Long user_id){
        return ResponseEntity.ok(todoService.getAllUserTodoById(user_id));
    }

    @GetMapping("user_id/{user_id}/status/{todo_status}")
    public ResponseEntity<?> getAllUserTodoByStatus(@PathVariable("todo_status") String todo_status,
                                                    @PathVariable("user_id") Long user_id){
        return ResponseEntity.ok(todoService.getAllUserTodoByStatus(todo_status,user_id));
    }

    @GetMapping("user_id/{user_id}/data/{todo_date}")
    public ResponseEntity<?>  getAllUserTodoByDate(@PathVariable("todo_date")
             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate todo_date, @PathVariable("user_id") Long user_id){
        return ResponseEntity.ok(todoService.getAllUserTodoByDate(todo_date,user_id));
    }



    }
