package com.serverless.services;

import java.util.List;

import javax.inject.Inject;

import com.serverless.models.TodoItem;
import com.serverless.repositories.ITodoRepository;

/**
 * TodoService
 */
public class TodoService implements ITodoService {

    private ITodoRepository _todoRepository;
    
    @Inject
    public TodoService(ITodoRepository todoRepository){
        _todoRepository = todoRepository;
    }

    @Override
    public TodoItem getTodoItem(String id) {
        return _todoRepository.getTodoItem(id);
    }

    @Override
    public boolean setTodoItem(TodoItem todoItem) {
        return _todoRepository.setTodoItem(todoItem);
    }

    @Override
    public boolean deleteTodoItem(String id) {
        return _todoRepository.deleteTodoItem(id);
    }

    @Override
    public List<TodoItem> searchTodoItem(String key) {
        return _todoRepository.searchTodoItem(key);
    }

    
}