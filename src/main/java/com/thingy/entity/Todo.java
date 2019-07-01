package com.thingy.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class Todo {
  @Id
  private ObjectId id;

  private String title;
  private String description;
  private boolean completed;

  public String getId() {
    return id.toHexString();
  }
}
