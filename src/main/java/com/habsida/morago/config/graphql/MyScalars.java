//package com.habsida.morago.config.graphql;
//
//import graphql.schema.*;
//import jakarta.servlet.http.Part;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.graphql.execution.RuntimeWiringConfigurer;
//
//import java.io.IOException;
//
//@Configuration
//public class MyScalars {
//    @Bean
//    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
//        return builder -> builder.scalar(FileUpload);
//    }
//
//    //    @Bean
////    public GraphQLScalarType FileUploadScalar() {
////        return FileUpload;
////    }
//    public static final GraphQLScalarType FileUpload = GraphQLScalarType.newScalar().name("FileUpload").description("A file part in a multipart request").coercing(new Coercing<FileUpload, Void>() {
//
//        @Override
//        public Void serialize(Object dataFetcherResult) {
//            throw new CoercingSerializeException("Upload is an input-only type");
//        }
//
//        @Override
//        public FileUpload parseValue(Object input) {
//            if (input instanceof Part) {
//                Part part = (Part) input;
//                try {
//                    String contentType = part.getContentType();
//                    byte[] content = new byte[part.getInputStream().available()];
//                    part.delete();
//                    return new FileUpload(contentType, content);
//
//                } catch (IOException e) {
//                    throw new CoercingParseValueException("Couldn't read content of the uploaded file");
//                }
//            } else if (null == input) {
//                return null;
//            } else {
//                throw new CoercingParseValueException("Expected type " + Part.class.getName() + " but was " + input.getClass().getName());
//            }
//        }
//
//        @Override
//        public FileUpload parseLiteral(Object input) {
//            throw new CoercingParseLiteralException("Must use variables to specify Upload values");
//        }
//    }).build();
//}