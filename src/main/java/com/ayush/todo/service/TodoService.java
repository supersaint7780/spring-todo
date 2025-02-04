package com.ayush.todo.service;

import org.springframework.stereotype.Service;

import com.ayush.todo.exception.TodoNotFoundException;
import com.ayush.todo.model.Todo;
import com.ayush.todo.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Iterable<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Iterable<Todo> getCompletedTodos() {
        return todoRepository.findByCompleted(true);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public void markTodoAsCompleted(Long id) {
        Todo todo = getTodoById(id);
        todo.setCompleted(true);
        todoRepository.save(todo);
    }

    public Todo getTodoById(Long id) {
        return todoRepository
                .findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo with id " + id + " not found"));
    }

    public void updateTodoById(Long id, Todo todo) {
        Todo todoToUpdate = getTodoById(id);
        todoToUpdate.setTitle(todo.getTitle());
        todoToUpdate.setDescription(todo.getDescription());
        todoToUpdate.setCompleted(todo.isCompleted());
        todoRepository.save(todoToUpdate);
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}
