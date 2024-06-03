package com.habsida.morago.config.graphql;


//
//
//import graphql.schema.GraphQLScalarType;
//import graphql.servlet.core.ApolloScalars;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GraphqlConfig {
////    private  graphql.servlet.core.ApolloScalars scalars;
//    @Bean
//    public GraphQLScalarType uploadScalar() {
////        GraphQLScalarType.newScalar()
////        graphql.schema.GraphQLScalarType.newScalar().name("zaha");
//        return  ApolloScalars.Upload;
//    }
//}



import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class GraphqlConfig {

    public static final GraphQLScalarType DateScalar = GraphQLScalarType.newScalar()
            .name("Upload")
            .description("A custom scalar that handles Date")
            .coercing(new Coercing() {} )
            .build();
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder -> builder.scalar(DateScalar);
    }
}