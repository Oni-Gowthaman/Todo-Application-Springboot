package com.shirooni.todo;

import com.shirooni.todo.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @GetMapping("/home")
    String todo() {
        //todoService.GetTodo();
        return "This is a todo controller";
    }

    @GetMapping ("/id")
    String getTodoId() {
        return "This is a todo Id";
    }

    //path variable
    @GetMapping ("/{id}")
    ResponseEntity<Todo> getTodoId(@PathVariable Long id) {
        try{
            Todo gotId=todoService.getTodoById(id);
            return new ResponseEntity<>(gotId, HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //request param ?id=4
//    @GetMapping ("")
//    String getTodoIdnumberparam(@RequestParam("id") int idnum) {
//        return "This is a todo Id "+idnum;
//    }

    //request body for secure data transfer
    @PostMapping("/create")
    ResponseEntity<Todo> CreateUser(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    ResponseEntity<Todo> UpdateTodo(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.ACCEPTED);
    }

    @DeleteMapping ("/delete/{id}")
    void DeleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
    }

    @GetMapping
    ResponseEntity<List<Todo>> getAllTodos() {
        return new ResponseEntity<List<Todo>>(todoService.getAllTodos(), HttpStatus.OK);
    }

}
