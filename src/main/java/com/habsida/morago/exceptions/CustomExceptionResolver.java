//package com.habsida.morago.exceptions;
//
//import graphql.GraphQLError;
//import graphql.GraphqlErrorBuilder;
//import graphql.execution.NonNullableValueCoercedAsNullException;
//import graphql.schema.CoercingParseValueException;
//import graphql.schema.DataFetchingEnvironment;
//import jakarta.persistence.EntityNotFoundException;
//import org.hibernate.exception.ConstraintViolationException;
//import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
//import org.springframework.graphql.execution.ErrorType;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Collectors;
//
//@Component
//public class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {
//
//    @Override
//    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
//        if (ex instanceof GraphqlEntityNotFoundException) {
//            return GraphqlErrorBuilder.newError()
//                    .errorType(ErrorType.NOT_FOUND)
//                    .message(ex.getMessage())
////                    .path(env.getExecutionStepInfo().getPath())
////                    .location(env.getField().getSourceLocation())
//                    .build();
//        }
//        if (ex instanceof NullPointerException) {
//            return GraphqlErrorBuilder.newError(env)
//                    .message("CoercingParseValueException")
//                    .errorType(ErrorType.NOT_FOUND).build();
//        }
//        else {
//            return null;
//
//
//        }
//    }
//}