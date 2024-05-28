package com.habsida.morago.exceptions;


import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import org.springframework.stereotype.Component;

import java.util.List;

public class GraphqlExceptionHandler extends RuntimeException implements GraphQLError {


    public GraphqlExceptionHandler(String message) {
        super(message);
    }
    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }
}
