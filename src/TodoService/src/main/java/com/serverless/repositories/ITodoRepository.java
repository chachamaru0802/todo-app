package com.serverless.repositories;

import com.serverless.models.TodoItem;

public interface ITodoRepository {

    TodoItem getTodoItem(String id) ;

    boolean setTodoItem(TodoItem todoItem);

    boolean deleteTodoItem(String id);
}