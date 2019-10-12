package com.serverless.config;

import javax.inject.Singleton;

import com.serverless.repositories.ITodoRepository;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    ITodoRepository getTodoRepository();
}