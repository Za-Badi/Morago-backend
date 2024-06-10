package com.habsida.morago.exceptions;

import graphql.*;
import graphql.schema.DataFetchingEnvironment;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;


//@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {
    @GraphQlExceptionHandler
    public GraphQLError handle(EntityNotFoundException ex, DataFetchingEnvironment env) {
        return GraphQLError.newError()
                .errorType(ErrorType.DataFetchingException)
                .message("Internal Server Error")
                .errorType(ErrorType.DataFetchingException)
//                .path(env.getExecutionStepInfo().getPath())
//                .location(env.getField().getSourceLocation())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(GraphqlException ex) {
        return GraphQLError.newError()
                .errorType(ErrorType.DataFetchingException)
                .message(ex.getMessage())
                .errorType(ErrorType.DataFetchingException)
                .build();
    }
    @GraphQlExceptionHandler
    public GraphQLError handle(RuntimeException ex) {
        return GraphQLError.newError()
                .errorType(ErrorType.DataFetchingException)
                .message("Internal Server Error")
                .errorType(ErrorType.DataFetchingException)
                .build();
    }
}