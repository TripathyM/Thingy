package com.thingy.service;

import com.thingy.model.Todo;
import com.thingy.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
  private final TodoRepository todoRepository;

  public List<Todo> getAllTodos() {
    return todoRepository.findAll();
  }

  public Todo createTodo(Todo todo) {
    todo.set_id(ObjectId.get());
    todoRepository.save(todo);

    return todo;
  }
}
