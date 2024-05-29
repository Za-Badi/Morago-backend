package com.habsida.morago.configs;

import com.habsida.morago.resolvers.UserResolver;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.InputStreamReader;

@Configuration
public class GraphQLConfiguration {
    private final UserResolver userResolver;
    public GraphQLConfiguration(UserResolver userResolver) {
        this.userResolver = userResolver;
    }
    @Bean
    public GraphQL graphQL() {
        return GraphQL.newGraphQL(schema()).build();
    }
    @Bean
    public GraphQLSchema schema() {
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        SchemaParser schemaParser = new SchemaParser();
        InputStream schemaInputStream = getClass().getResourceAsStream("/schema.graphqls");
        typeRegistry.merge(schemaParser.parse(new InputStreamReader(schemaInputStream)));
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }
    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("getAllUsers", env -> userResolver.getAllUsers())
                        .dataFetcher("getUserById", env -> userResolver.getUserById((String) env.getArgument("id"))))
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("addUser", env -> userResolver.addUser(env.getArgument("user")))
                        .dataFetcher("updateUser", env -> userResolver.updateUser((String) env.getArgument("id"), env.getArgument("user")))
                        .dataFetcher("deleteUser", env -> userResolver.deleteUser((String) env.getArgument("id"))))
                .build();
    }
}