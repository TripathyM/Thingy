package com.thingy.service;

import com.thingy.entity.Todo;
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
    todo.setId(ObjectId.get());
    todoRepository.save(todo);

    return todo;
  }

  public void deleteTodo(String todoId) {
    todoRepository.deleteById(todoId);
  }

  public Todo updateTodo(Todo updatedTodo) {
    return todoRepository.save(updatedTodo);
  }
}
