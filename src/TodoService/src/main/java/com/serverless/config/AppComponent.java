package com.serverless.config;

import javax.inject.Singleton;

import com.serverless.services.ITodoService;

import dagger.Component;

/**
 * Dagger Componentクラス
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    ITodoService todoService();
}