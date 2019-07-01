package com.thingy.service;

import com.thingy.entity.Todo;
import com.thingy.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
class TodoServiceTest {

  @Mock
  TodoRepository todoRepository;

  private TodoService todoService;

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

  @Test
  void shouldPopulateIdFieldOfTodoAndSave() {
    Todo todo = new Todo();

    todoService.createTodo(todo);

    assertThat(todo.getId()).isNotNull();
    verify(todoRepository).save(todo);
  }

  @Test
  void shouldUpdateTodo() {
    Todo updatedTodo = new Todo();
        updatedTodo.setTitle("updated todo");
        updatedTodo.setDescription("updated todo description");

    when(todoRepository.save(any(Todo.class))).thenReturn(updatedTodo);

    Todo actualUpdatedTodo = todoService.updateTodo(updatedTodo);

    assertThat(actualUpdatedTodo.getDescription()).isEqualTo(updatedTodo.getDescription());
    assertThat(actualUpdatedTodo.getTitle()).isEqualTo(updatedTodo.getTitle());

    verify(todoRepository).save(updatedTodo);
  }

  @Test
  @DisplayName("should delete todo by using todoId")
  void shouldDeleteTodoByTodoId() {
    Todo todo1 = new Todo();

    todoService.createTodo(todo1);
    todoService.deleteTodo(todo1.getId());

    verify(todoRepository).deleteById(todo1.getId());
  }
}
