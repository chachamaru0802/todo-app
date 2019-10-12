package com.serverless;

import java.util.Map;

import javax.inject.Inject;

import com.amazonaws.services.lambda.runtime.Context;
import com.serverless.config.*;
import com.serverless.config.AppComponent;
import com.serverless.repositories.ITodoRepository;

import org.apache.log4j.Logger;

import lombok.val;

public class GetTodolHandler extends HandlerBase<Map<String, Object>, ApiGatewayResponse> {

    private Logger _logger = Logger.getLogger(GetTodolHandler.class);

    
    ITodoRepository _todoRepository;

    @Inject
    public GetTodolHandler() {
        AppComponent appComponent = DaggerAppComponent.builder().build();
        _todoRepository = appComponent.getTodoRepository();
    }

    @Override
    protected ApiGatewayResponse process(Map<String, Object> request, Context context) {

        String id = "3";

        val result = _todoRepository.getTodoItem(id);

        return ApiGatewayResponse.responseBuild(result);
    }

}