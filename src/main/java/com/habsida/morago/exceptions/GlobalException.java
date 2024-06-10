package com.habsida.morago.exceptions;




import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class GlobalException extends RuntimeException{
    public GlobalException(String message) {
        super(message);
    }



}
