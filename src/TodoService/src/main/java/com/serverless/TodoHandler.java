package com.serverless;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    /**
     * Todoを１件取得します。
     * 
     * @param request
     * @param context
     * @return
     */
    public ApiGatewayResponse get(ApiGatewayRequest request, Context context) {
        String id = Optional.ofNullable(request.getPathParameters()).map(x -> x.get("id")).orElse(null);

        _logger.info("pathParamerter: " + id);

        val body = _appComponent.todoService().getTodoItem(id);
        val result = ApiGatewayResponse.responseBuild(body);

        return result;
    }

    /**
     * Todoリストのタイトルを検索します。
     * 
     * @param request
     * @param context
     * @return
     */
    public ApiGatewayResponse search(ApiGatewayRequest request, Context context) {
        String key = Optional.ofNullable(request.getPathParameters()).map(x -> x.get("key")).orElse(null);

        _logger.info("list start");

        val body = _appComponent.todoService().searchTodoItem(key);

        _logger.info("body: " + body);

        val result = ApiGatewayResponse.responseBuild(body);

        return result;
    }

    /**
     * Todoリストを取得します。
     * 
     * @param request
     * @param context
     * @return
     */
    public ApiGatewayResponse list(Map<String, Object> input, Context context) {

        _logger.info("list start");

        val body = _appComponent.todoService().searchTodoItem(null);

        _logger.info("body: " + body);

        val result = ApiGatewayResponse.responseBuild(body);

        return result;
    }

    /***
     * Todoを１件新規に登録します。
     * 
     * @param request
     * @param context
     * @return
     */
    public ApiGatewayResponse create(ApiGatewayRequest request, Context context) {
        try {
            val json = request.getBody();
            val mapper = new ObjectMapper();
            val todo = mapper.readValue(json, TodoItem.class);

            val body = _appComponent.todoService().setTodoItem(todo);
            ApiGatewayResponse.responseBuild(body);

            return ApiGatewayResponse.responseSuccess();

        } catch (Exception e) {
            _logger.error(e);

            return ApiGatewayResponse.responseServerError();
        }
    }

    /**
     * 既存のTodoを更新します。
     * 
     * @param request
     * @param context
     * @return
     */
    public ApiGatewayResponse update(ApiGatewayRequest request, Context context) {
        try {
            val json = request.getBody();
            val mapper = new ObjectMapper();
            val todo = mapper.readValue(json, TodoItem.class);

            val body = _appComponent.todoService().setTodoItem(todo);
            ApiGatewayResponse.responseBuild(body);

            return ApiGatewayResponse.responseSuccess();

        } catch (Exception e) {
            _logger.error(e);

            return ApiGatewayResponse.responseServerError();
        }
    }

    /***
     * Todoを１件削除します。
     * 
     * @param request
     * @param context
     * @return
     */
    public ApiGatewayResponse delete(ApiGatewayRequest request, Context context) {
        try {
            String id = Optional.ofNullable(request.getPathParameters()).map(x -> x.get("id")).orElse(null);

            _logger.info("pathParamerter: " + id);

            _appComponent.todoService().deleteTodoItem(id);

            return ApiGatewayResponse.responseSuccess();

        } catch (Exception e) {
            _logger.error(e);

            return ApiGatewayResponse.responseServerError();
        }
    }

}