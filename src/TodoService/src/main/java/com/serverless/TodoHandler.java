package com.serverless;

import java.util.Map;

import javax.inject.Inject;

import com.amazonaws.services.lambda.runtime.Context;
import com.serverless.config.*;
import com.serverless.models.TodoItem;
import com.serverless.repositories.ITodoRepository;

import org.apache.log4j.Logger;

import lombok.val;

public class TodoHandler {

    private Logger _logger = Logger.getLogger(TodoHandler.class);

    private AppComponent _appComponent;

    public TodoHandler() {
        _appComponent = DaggerAppComponent.builder().build();
    }

    public ApiGatewayResponse getTodoItem(Map<String, Object> input, Context context) {
        val body = _appComponent.todoService().getTodoItem("aaa");
        val result = ApiGatewayResponse.responseBuild(body);

        return result;
    }

}