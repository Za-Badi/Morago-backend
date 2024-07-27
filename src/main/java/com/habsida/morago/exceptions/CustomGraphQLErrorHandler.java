package com.habsida.morago.exceptions;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.core.DefaultGraphQLErrorHandler;
import graphql.servlet.core.GraphQLErrorHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
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
            if (exception instanceof AccessDeniedException ||
                    exception instanceof BadCredentialsException ||
                    exception instanceof AccountStatusException ||
                    exception instanceof SignatureException ||
                    exception instanceof ExpiredJwtException ||
                    exception instanceof AuthenticationException ||
                    exception instanceof NullPointerException ||
                    exception instanceof EntityNotFoundException ||
                    exception instanceof IllegalArgumentException ||
                    exception instanceof IOException) {
                return new ExceptionGraphql(exception.getMessage());
            }
            if (exception instanceof ExceptionGraphql) {
                return (ExceptionGraphql) exception;
            }
        }
        return defaultHandler.processErrors(List.of(error)).get(0);
    }
}
