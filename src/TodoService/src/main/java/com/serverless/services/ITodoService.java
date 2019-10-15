package com.serverless.services;

import java.util.List;

import com.serverless.models.TodoItem;

public interface ITodoService {
    List<TodoItem> searchTodoItem(String key);

    TodoItem getTodoItem(String id);
    
    boolean setTodoItem(TodoItem todoItem);

    boolean deleteTodoItem(String id);
}