package com.serverless;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.serverless.models.TodoItem;

import org.apache.log4j.Logger;

import lombok.val;


public class GetTodolHandler extends HandlerBase<Map<String, Object>, ApiGatewayResponse> {

    private Logger _logger = Logger.getLogger(GetTodolHandler.class);
   
    @Override
    protected ApiGatewayResponse process(Map<String, Object> request, Context context) {

        val result = new TodoItem();

        result.setTitle("title");
        result.setContents("Contents");

        return ApiGatewayResponse.responseBuild(result);
    }
}