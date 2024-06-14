package com.habsida.morago.exceptions;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class ExceptionGraphql extends RuntimeException implements GraphQLError{

    public ExceptionGraphql(String message) {
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
