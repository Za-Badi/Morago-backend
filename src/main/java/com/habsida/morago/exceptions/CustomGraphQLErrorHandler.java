package com.habsida.morago.exceptions;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.core.DefaultGraphQLErrorHandler;
import graphql.servlet.core.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    private final DefaultGraphQLErrorHandler defaultHandler = new DefaultGraphQLErrorHandler();

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors.stream()
                .map(this::processError)
                .collect(Collectors.toList());
    }

    private GraphQLError processError(GraphQLError error) {
        if (error instanceof ExceptionWhileDataFetching) {
            Throwable exception = ((ExceptionWhileDataFetching) error).getException();
            if (exception instanceof AuthenticationException) {
                return new ExceptionGraphql(((AuthenticationException) exception).getMessage());
            }
            if (exception instanceof NullPointerException) {
                return new ExceptionGraphql(((NullPointerException) exception).getMessage());
            }
        }
        return defaultHandler.processErrors(List.of(error)).get(0);
    }
}