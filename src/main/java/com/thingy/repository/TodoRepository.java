package com.thingy.repository;

import com.thingy.model.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {
  Todo findBy_id(ObjectId id);
}
