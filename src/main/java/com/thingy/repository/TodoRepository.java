package com.thingy.repository;

import com.thingy.entity.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TodoRepository extends MongoRepository<Todo, String> {
    @Override
    void deleteById(String s);
}
