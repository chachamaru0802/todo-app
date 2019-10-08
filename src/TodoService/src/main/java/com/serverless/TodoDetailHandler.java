package com.serverless;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.apache.log4j.Logger;

public class TodoDetailHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = Logger.getLogger(TodoDetailHandler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: " + input);
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody("responseBody")
				.build();
	}
    
}