package com.thingy.controller;

import com.thingy.entity.Todo;
import com.thingy.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
