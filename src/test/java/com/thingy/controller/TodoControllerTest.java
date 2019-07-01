package com.thingy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thingy.entity.Todo;
import com.thingy.service.TodoService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

  @MockBean
  private TodoService todoService;

  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Should return an array of todos in the response")
  void shouldReturnAllTodosInResponse() throws Exception {
    Todo todo1 = createTodo("todo1", "description 1");
    Todo todo2 = createTodo("todo2", "description 2");
    when(todoService.getAllTodos()).thenReturn(List.of(todo1, todo2));

    mockMvc.perform(get("/todos"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.[0].title").value("todo1"))
      .andExpect(jsonPath("$.[0].description").value("description 1"))
      .andExpect(jsonPath("$.[0].completed").value(false))
      .andExpect(jsonPath("$.[1].title").value("todo2"))
      .andExpect(jsonPath("$.[1].description").value("description 2"))
      .andExpect(jsonPath("$.[1].completed").value(false));
  }

  @Test
  @DisplayName("should create todo and return back the saved todo in response")
  void shouldReturnTodo() throws Exception {
    Todo todo = createTodo("title", "Hello todo");
    when(todoService.createTodo(todo)).thenReturn(todo);

    mockMvc.perform(
      post("/todos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(todo)))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.title").value("title"))
      .andExpect(jsonPath("$.description").value("Hello todo"));

    verify(todoService).createTodo(todo);
  }

  @Test
  @DisplayName("should delete todo using todoId")
  void shouldDeleteTodo() throws Exception {
    Todo todo = createTodo("test", "test todo");
    String todoId = todo.getId();
    mockMvc.perform(delete("/todos/" + todoId)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());

    verify(todoService).deleteTodo(todoId);
  }

  @Test
  @DisplayName("should be able to update todo with the updated value")
  void shouldUpdateTodo() throws Exception {
    Todo todo = createTodo("test", "test todo");
    Todo updatedTodo = createTodo("updated test", "updated test todo");
    String todoId = todo.getId();
    when(todoService.updateTodo(updatedTodo)).thenReturn(updatedTodo);

    mockMvc.perform(put("/todos/" + todoId)
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(updatedTodo)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title").value("updated test"))
      .andExpect(jsonPath("$.description").value("updated test todo"));

    verify(todoService).updateTodo(updatedTodo);
  }

  private Todo createTodo(String title, String description) {
    Todo todo = new Todo();
    todo.setId(ObjectId.get());
    todo.setTitle(title);
    todo.setDescription(description);

    return todo;
  }
}
