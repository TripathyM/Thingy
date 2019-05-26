package com.thingy.service;

import com.thingy.model.Todo;
import com.thingy.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@MockitoSettings
class TodoServiceTest {

  @Mock
  TodoRepository todoRepository;

  TodoService todoService;

  @BeforeEach
  void setUp() {
    todoService = new TodoService(todoRepository);
  }

  @Test
  @DisplayName("should return all todos")
  void shouldReturnAllTodos() {
    Todo todo1 = new Todo();
    Todo todo2 = new Todo();
    List<Todo> todos = List.of(todo1, todo2);
    when(todoRepository.findAll()).thenReturn(todos);

    final List<Todo> allTodos = todoService.getAllTodos();
    assertThat(allTodos).isEqualTo(todos);
  }
}