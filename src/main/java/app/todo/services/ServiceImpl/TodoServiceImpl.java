package app.todo.services.ServiceImpl;

import app.todo.dto.NewToDoDTO;
import app.todo.dto.UpdateToDoDTO;
import app.todo.dto.UpdateTodoDueDateDTO;
import app.todo.model.Status;
import app.todo.model.Todo;
import app.todo.repository.TodoRepository;
import app.todo.repository.UserRepository;
import app.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TodoServiceImpl implements TodoService {

    final
    TodoRepository todoRepository;
    final
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Todo addTodo(NewToDoDTO newToDoDTO) {
        Todo todo  = new Todo();
        todo.setTodo_name(newToDoDTO.getTodo_name());
        todo.setTodo_description(newToDoDTO.getTodo_description());
        todo.setTodo_creation_date(LocalDate.now());
        todo.setTodo_due_date(newToDoDTO.getTodo_due_date());
        todo.setTodo_status(Status.PENDING.toString());
        Long user_id = newToDoDTO.getUser_id();

        if(userRepository.findById(user_id).isPresent()){
            todo.setUser(userRepository.findById(user_id).get());
        }
        else{
            logger.error("user not found");
        }
       return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long todo_id) {
        todoRepository.deleteById(todo_id);
    }

    @Override
    public List<Todo> getAllUserTodoById(Long user_id) {
        return todoRepository.findAll().stream().filter(e->e.getUser().getId()==user_id).collect(Collectors.toList());
    }

    @Override
    public List<Todo> getAllUserTodoByDate(LocalDate localDate, Long user_id) {
        List<Todo> user_todos = todoRepository.findAll().stream().filter(
                e->e.getUser().getId()==user_id
        ).collect(Collectors.toList());
        return user_todos.stream().filter(e->e.getTodo_creation_date().isEqual(localDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> getAllUserTodoByStatus(String todo_status,Long user_id) {
        List<Todo> todo_list= new ArrayList<>();
        List<Todo> user_todos = todoRepository.findAll().stream().filter(
                e->e.getUser().getId()==user_id
        ).collect(Collectors.toList());

        if(todo_status.equalsIgnoreCase("completed"))
            todo_list = user_todos.stream().filter(e->e.getTodo_status().equals(Status.COMPLETED.toString()))
                    .collect(Collectors.toList());
        if(todo_status.equalsIgnoreCase("pending"))
            todo_list = user_todos.stream().filter(e->e.getTodo_status().equals(Status.PENDING.toString()))
                    .collect(Collectors.toList());
        if(todo_status.equalsIgnoreCase("overdue"))
            todo_list = user_todos.stream().filter(e->e.getTodo_status().equals(Status.PENDING.toString()))
                    .collect(Collectors.toList());

       return todo_list;
    }

    @Override
    public Todo updateTodo(UpdateToDoDTO updateToDoDTO) {

        return todoRepository.findById(updateToDoDTO.getTodo_id())
                .map(l-> {
                    l.setTodo_name(updateToDoDTO.getTodo_name());
                    l.setTodo_description(updateToDoDTO.getTodo_name());
                    return todoRepository.save(l);
                })
                .orElseGet(() -> {
                    return null;

                });
    }

    @Override
    public Todo updateTodoDate(UpdateTodoDueDateDTO updateTodoDueDateDTO) {

        return todoRepository.findById(updateTodoDueDateDTO.getTodo_id())
                .map(l-> {
                    l.setTodo_due_date(updateTodoDueDateDTO.getTodo_due_date());
                    return todoRepository.save(l);
                })
                .orElseGet(() -> {
                    return null;

                });
    }
    }




