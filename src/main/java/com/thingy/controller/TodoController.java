package com.thingy.controller;

import com.thingy.entity.Todo;
import com.thingy.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {
  private final TodoService service;

  @GetMapping
  public List<Todo> getAllTodos() {
    return service.getAllTodos();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Todo createTodo(@RequestBody Todo todo) {
    return service.createTodo(todo);
  }

  @PutMapping(value = "/{todoId}")
  public Todo updateTodo(@RequestBody Todo updatedTodo, @PathVariable String todoId) {
    Todo todo = service.updateTodo(updatedTodo);
    System.out.println(todo);
    return todo;
  }

  @DeleteMapping(value = "/{todoId}")
  public void deleteTodo(@PathVariable String todoId) {
    service.deleteTodo(todoId);
  }

}
