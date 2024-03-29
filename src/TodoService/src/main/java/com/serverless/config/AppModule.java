package com.serverless.config;

import javax.inject.Singleton;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.serverless.repositories.ITodoRepository;
import com.serverless.repositories.TodoRepository;
import com.serverless.services.ITodoService;
import com.serverless.services.TodoService;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger DI設定クラス
 */
@Module
public class AppModule {
    @Provides
    @Singleton
    public ITodoService provideTodoService(ITodoRepository todoRepository) {
        return new TodoService(todoRepository);
    }

    @Provides
    @Singleton
    public ITodoRepository provideTodoRepository(AmazonDynamoDB dynamoDB) {
        return new TodoRepository(dynamoDB);
    }

    @Provides
    @Singleton
    public AmazonDynamoDB provideAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }
}