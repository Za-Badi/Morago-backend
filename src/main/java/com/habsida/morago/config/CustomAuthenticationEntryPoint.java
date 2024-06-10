package com.habsida.morago.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");

        // Create a list of errors
        List<GraphQLError> errors = new ArrayList<>();
        GraphQLError error = GraphqlErrorBuilder.newError()
                .message("The Token has expired")
                .build();
        errors.add(error);
        // Build the error response structure
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        errorResponse.put("data", Collections.singletonMap("runTest", null));

        // Serialize the error response as JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String errorJson = objectMapper.writeValueAsString(errorResponse);

        // Write the JSON error response to the response output stream
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(errorJson);
        writer.flush();
    }


}
