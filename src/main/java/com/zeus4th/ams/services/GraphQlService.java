package com.zeus4th.ams.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import com.zeus4th.ams.services.datafetcher.AllUsersDetails;
import com.zeus4th.ams.services.datafetcher.AllUserDataFetcher;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQlService {

  @Autowired
  private AllUserDataFetcher allUserDataFetcher;
  @Autowired
  private AllUsersDetails allUsersDetails;
  @Value("classpath:./graphql/schema.graphql")
  Resource resource;
  private GraphQL graphQL;


  @PostConstruct
  private void loadSchema() throws IOException {

    InputStream file = resource.getInputStream();

    // Get the graphql file
    TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(file);
    RuntimeWiring runtimeWiring = buildRuntimeWiring();
    GraphQLSchema graphQLSchema
        = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  private RuntimeWiring buildRuntimeWiring() {
    Map<String,DataFetcher> map = new HashMap<>();
    map.put("allUsers", allUserDataFetcher);
    map.put("allUserDetails",allUsersDetails);
//    map.put("getUserDetails",allUsersDetails);
    return RuntimeWiring.newRuntimeWiring()
        .type("Query",
            typeWiring -> typeWiring
                    .dataFetchers(map)
        )
        .build();
  }

  public ExecutionResult executeGraphQL(String query) {
    return graphQL.execute(query);
  }

}