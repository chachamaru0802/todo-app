package com.serverless.repositories;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.util.StringUtils;
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
     * Todo検索
     */
    public List<TodoItem> searchTodoItem(String key) {
        val scanExpression = new DynamoDBScanExpression();

        val list = _mapper.scan(TodoItem.class, scanExpression);

        val isKeyEmpty = StringUtils.isNullOrEmpty(key);

        val todos = list.stream().filter(x -> !x.isCompleted()).collect(Collectors.toList());

        if (isKeyEmpty) {
            return todos;
        }

        val result = todos.stream().filter(x -> x.getTitle().contains(key)).collect(Collectors.toList());

        return result;

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
        val datetime = new Date();

        val isEmptyId = StringUtils.isNullOrEmpty(todoItem.getId());

        if (isEmptyId) {
            todoItem.setCreateDatetime(datetime);
        }

        todoItem.setUpdateDatetime(datetime);

        _mapper.save(todoItem);

        return true;
    }

    /**
     * Todoを１件削除します。
     *
     * @param id
     * @return
     */
    public boolean deleteTodoItem(String id) {
        val todo = getTodoItem(id);
        _mapper.delete(todo);

        return true;
    }
}