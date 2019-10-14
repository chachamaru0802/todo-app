package com.serverless.repositories;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.serverless.models.TodoItem;

import lombok.val;

/**
 * Todoリポジトリ
 */
public class TodoRepository extends RepositoryBase implements ITodoRepository {

    private AmazonDynamoDB _dynamoDbClient;

    private DynamoDBMapper _mapper;

    @Inject
    public TodoRepository(AmazonDynamoDB amazonDynamoDB) {
        _dynamoDbClient = amazonDynamoDB;
        _mapper = new DynamoDBMapper(_dynamoDbClient);
    }

    /**
     * Todo 検索
     */
    public List<TodoItem> searchTodoItem(TodoItem todoItem) {
        val scan = new DynamoDBScanExpression();
        
        val todos = _mapper.scan(TodoItem.class, scan);

        return todos;
    }

    /**
     * Todo 取得
     * 
     * @param id ID
     * @return Todo情報
     */
    public TodoItem getTodoItem(String id) {
        return _mapper.load(TodoItem.class, id);
    }

    /**
     * Todo 保存
     * 
     * @param todoItem
     * @return
     */
    public boolean setTodoItem(TodoItem todoItem) {
        val datetime = LocalDateTime.now();
        val isEmptyId = todoItem.getId().isEmpty();

        if(isEmptyId){
            todoItem.setCreateDatetime(datetime);
        }

        todoItem.setUpdateDatetime(datetime);

        _mapper.save(todoItem);

        return true;
    }

    /**
     * Todo 削除
     * 
     * @param todoItem
     * @return
     */
    public boolean deleteTodoItem(TodoItem todoItem) {
        _mapper.delete(todoItem);

        return true;
    }
}