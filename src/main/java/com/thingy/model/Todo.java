package com.thingy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Todo {
  @Id
  private ObjectId _id;

  private String title;
  private String description;
  private boolean completed;

  public String get_id() {
    return _id.toHexString();
  }
}
