package com.serverless.services;

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

    
}