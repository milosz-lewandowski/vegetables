package com.miloszlewandowski.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Singleton;

@Produces
@Singleton
public class VegetableDuplicateExceptionHandler implements ExceptionHandler<VegetableDuplicateException, HttpResponse<?>> {

    private final ErrorResponseProcessor<?> errorResponseProcessor;

    public VegetableDuplicateExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
        this.errorResponseProcessor = errorResponseProcessor;
    }

    @Override
    public HttpResponse<?> handle(HttpRequest request, VegetableDuplicateException exception) {
        ErrorContext errorContext = ErrorContext.builder(request)
                .cause(exception)
                .errorMessage(exception.getMessage())
                .build();
        return errorResponseProcessor.processResponse(errorContext, HttpResponse.unprocessableEntity());
    }
}