package com.serverless.repositories;

import java.util.List;

import com.serverless.models.TodoItem;

public interface ITodoRepository {

    List<TodoItem> searchTodoItem(String key);

    TodoItem getTodoItem(String id) ;

    boolean setTodoItem(TodoItem todoItem);

    boolean deleteTodoItem(String id);
}