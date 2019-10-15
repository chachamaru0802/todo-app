package com.serverless.services;

import com.serverless.models.TodoItem;

public interface ITodoService {
    TodoItem getTodoItem(String id);
    
    boolean setTodoItem(TodoItem todoItem);

    boolean deleteTodoItem(String id);
}