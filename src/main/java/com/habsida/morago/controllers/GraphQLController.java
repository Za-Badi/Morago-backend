package com.habsida.morago.controllers;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GraphQLController {
    private final GraphQL graphQL;
    @Autowired
    public GraphQLController(GraphQL graphQL) {
        this.graphQL = graphQL;
    }
    @GetMapping("/graphql")
    public Object graphqlGet(@RequestParam("query") String query) {
        ExecutionResult result = graphQL.execute(query);
        return result.toSpecification();
    }
    @PostMapping("/graphql")
    public Object graphqlPost(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        return result.toSpecification();
    }
}