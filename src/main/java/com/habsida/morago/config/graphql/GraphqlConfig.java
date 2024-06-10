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
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;



@Configuration
public class GraphqlConfig {

//    @Bean
//    public GraphQLScalarType uploadScalarDefine() {
//        return ApolloScalars.Upload;
//
//    }
//public  final GraphQLScalarType Upload =
//        new GraphQLScalarType("Upload",
//                "A file part in a multipart request",
//                new Coercing<Part, Void>() {
//                    @Override
//                    public Void serialize(Object dataFetcherResult) {
//                        throw new CoercingSerializeException("Upload is an input-only type");
//                    }
//
//                    @Override
//                    public Part parseValue(Object input) {
//                        if (input instanceof Part) {
//                            return (Part) input;
//                        } else if (null == input) {
//                            return null;
//                        } else {
//                            throw new CoercingParseValueException("Expected type " +
//                                    Part.class.getName() +
//                                    " but was " +
//                                    input.getClass().getName());
//                        }
//                    }
//
//                    @Override
//                    public Part parseLiteral(Object input) {
//                        throw new CoercingParseLiteralException(
//                                "Must use variables to specify Upload values");
//                    }
//                });


//    xxxxxxxxxxxxxx

//    public  final GraphQLScalarType DateScalar = GraphQLScalarType.newScalar()
//            .name("Upload")
//            .description("A custom scalar that handles Date")
//            .coercing(
//                    new Coercing<Part, Void>() {
//                        @Override
//                        public Void serialize(Object dataFetcherResult) {
//                            throw new CoercingSerializeException("Upload is an input-only type");
//                        }
//
//                        @Override
//                        public Part parseValue(Object input) {
//                            if (input instanceof Part) {
//                                return (Part) input;
//                            } else if (null == input) {
//                                return null;
//                            } else {
//                                throw new CoercingParseValueException("Expected type " +
//                                        Part.class.getName() +
//                                        " but was " +
//                                        input.getClass().getName());
//                            }
//                        }
//
//                        @Override
//                        public Part parseLiteral(Object input) {
//                            throw new CoercingParseLiteralException(
//                                    "Must use variables to specify Upload values");
//                        }
//                    }
//            )
//            .build();
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder -> builder.scalar(ApolloScalars.Upload);
    }

}