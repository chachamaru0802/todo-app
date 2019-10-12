package com.serverless;

import java.util.Map;

import javax.inject.Inject;

import com.amazonaws.services.lambda.runtime.Context;
import com.serverless.config.*;
import com.serverless.config.AppComponent;
import com.serverless.models.TodoItem;
import com.serverless.repositories.ITodoRepository;

import org.apache.log4j.Logger;

import lombok.val;

public class TodolHandler  {

  //  private Logger _logger = Logger.getLogger(GetTodolHandler.class);


    // protected TodoItem process(Map<String, Object> request, Context context) {

    //     // String id = "3";

    //     // val result = _todoRepository.getTodoItem(id);

    //     val todo = new TodoItem(){
    //         {
    //             setTitle("title");
    //             setContents("Contents");
    //         }
    //     };
        

    //     return todo;
    // }

    public ApiGatewayResponse getTodoItem(){

        val todo = new TodoItem(){
            {
                setTitle("title");
                setContents("Contents");
            }
        };
        
        val result = ApiGatewayResponse
            .builder()
            .setStatusCode(200)
            .setObjectBody(todo)
            .build();

        return result;
    }

}