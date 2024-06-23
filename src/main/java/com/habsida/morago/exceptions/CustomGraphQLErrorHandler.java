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
            if (exception instanceof AccessDeniedException) {
                return new ExceptionGraphql(((AccessDeniedException) exception).getMessage());
            }
            if (exception instanceof BadCredentialsException) {
                return new ExceptionGraphql(((BadCredentialsException) exception).getMessage());
            }
            if (exception instanceof AccountStatusException) {
                return new ExceptionGraphql(((AccountStatusException)exception).getMessage());
            }
            if (exception instanceof SignatureException) {
                return new ExceptionGraphql(((SignatureException) exception).getMessage());
            }
            if (exception instanceof ExpiredJwtException) {
                return new ExceptionGraphql(((ExpiredJwtException) exception).getMessage());
            }
            if (exception instanceof AuthenticationException) {
                return new ExceptionGraphql(((AuthenticationException) exception).getMessage());
            }
            if (exception instanceof NullPointerException) {
                return new ExceptionGraphql(((NullPointerException) exception).getMessage());
            }
            if (exception instanceof EntityNotFoundException) {
                return new ExceptionGraphql(((EntityNotFoundException) exception).getMessage());
            }
            if (exception instanceof ExceptionGraphql) {
                return new ExceptionGraphql(((ExceptionGraphql) exception).getMessage());
            }
            if (exception instanceof IllegalArgumentException) {
                return new ExceptionGraphql(((IllegalArgumentException) exception).getMessage());
            }
            if (exception instanceof IOException) {
                return new ExceptionGraphql(((IOException) exception).getMessage());
            }
        }
        return defaultHandler.processErrors(List.of(error)).get(0);
    }
}