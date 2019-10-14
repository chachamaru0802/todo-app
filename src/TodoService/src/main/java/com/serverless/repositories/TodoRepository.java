package com.serverless.repositories;

import javax.inject.Inject;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.serverless.models.TodoItem;

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
        _mapper.save(todoItem);

        return true;
    }

}