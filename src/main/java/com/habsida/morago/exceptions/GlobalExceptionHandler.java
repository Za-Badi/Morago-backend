package com.habsida.morago.exceptions;
import graphql.*;
import graphql.execution.NonNullableValueCoercedAsNullException;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.DataFetchingEnvironment;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

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
    public GraphQLError handle(RuntimeException ex) {
        return GraphQLError.newError()
                .errorType(ErrorType.DataFetchingException)
                .message("Internal Server Error")
                .errorType(ErrorType.DataFetchingException)
                .build();
    }
}