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


import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.schema.*;
import jakarta.servlet.http.Part;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;


@Configuration
public class GraphqlConfig {
    @Bean
    public GraphQLScalarType uploadScalar() {
        return ApolloScalars.Upload;
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder -> builder.scalar(Upload);
    }

    public final GraphQLScalarType Upload = ApolloScalars.Upload;

}