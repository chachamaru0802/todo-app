package com.serverless.repositories;

import javax.inject.Inject;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.serverless.models.TodoItem;
import com.serverless.utilities.IAwsUtility;

import lombok.val;

/**
 * Todoリポジトリ
 */
public class TodoRepository extends RepositoryBase implements ITodoRepository {

    private AmazonDynamoDB _dynamoDbClient;

    // @Inject
    public TodoRepository() {
        // _dynamoDbClient = awsUtility.getDynamoDBClient();
    }

    /**
     * Todo 取得
     * 
     * @param id ID
     * @return Todo情報
     */
    public TodoItem getTodoItem(String id) {
        val result = new TodoItem();

        result.setTitle(id);
        result.setContents("Contents");

        return result;
    }

}