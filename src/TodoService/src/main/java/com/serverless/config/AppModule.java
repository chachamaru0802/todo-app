package com.serverless.config;


import javax.inject.Singleton;

import com.serverless.repositories.ITodoRepository;
import com.serverless.repositories.TodoRepository;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    @Provides
    @Singleton
    public ITodoRepository providTodoRepository(){
        return new TodoRepository();

    }    
}