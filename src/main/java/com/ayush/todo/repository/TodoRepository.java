package com.ayush.todo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.ayush.todo.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    @Query("SELECT * FROM todo WHERE completed = :b")
    Iterable<Todo> findByCompleted(boolean b);
}
