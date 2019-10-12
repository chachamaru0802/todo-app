package com.serverless.repositories;

import com.serverless.models.TodoItem;

import lombok.val;

/**
 * Todoリポジトリ
 */
public class TodoRepository implements ITodoRepository {

    /**
     * Todo 取得
     * 
     * @param id ID
     * @return Todo情報
     */
    public TodoItem getTodoItem(String id) {
        val result = new TodoItem();

        result.setTitle("title");
        result.setContents("Contents");

        return result;
    }

}