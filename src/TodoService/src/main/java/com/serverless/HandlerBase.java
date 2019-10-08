package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public abstract class HandlerBase<I, O> implements RequestHandler<I, O> {

    protected abstract O process(I request, Context context);

    protected void init() {

    }

    protected void destroy() {

    }

    @Override
    public final O handleRequest(I i, Context context) {

            init();

            O result = process(i, context);

            destroy();

            return result;

    }
}