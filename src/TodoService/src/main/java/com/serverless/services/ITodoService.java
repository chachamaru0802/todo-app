package com.serverless.services;

import com.serverless.models.TodoItem;

public interface ITodoService {
    TodoItem getTodoItem(String id);
    
}