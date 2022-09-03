package com.zeus4th.ams.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import com.zeus4th.ams.services.datafetcher.AllUsersDetailsDataFetcher;
import com.zeus4th.ams.services.datafetcher.AllUserDataFetcher;
import com.zeus4th.ams.services.datafetcher.ano.datafetchers.ChatMessageDataFetcher;
import com.zeus4th.ams.services.datafetcher.ano.datafetchers.ParticipantsDataFetcher;
import com.zeus4th.ams.services.datafetcher.ano.datafetchers.SessionDetailsDataFetcher;
import graphql.schema.DataFetcher;
import graphql.schema.visibility.NoIntrospectionGraphqlFieldVisibility;
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
  private AllUsersDetailsDataFetcher allUsersDetailsDataFetcher;

  @Autowired
  private SessionDetailsDataFetcher sessionDetailsDataFetcher;
  @Autowired
  private ParticipantsDataFetcher participantsDataFetcher;
  @Autowired
  private ChatMessageDataFetcher chatMessageDataFetcher;
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

    graphQL = GraphQL.newGraphQL(graphQLSchema).schema(graphQLSchema).build();
  }

  private RuntimeWiring buildRuntimeWiring() {
    Map<String,DataFetcher> map = new HashMap<>();
    map.put("allUsers", allUserDataFetcher);
    map.put("userDetails", allUsersDetailsDataFetcher);
    map.put("sessionDetails",sessionDetailsDataFetcher);
    map.put("chatMessages",chatMessageDataFetcher);
    map.put("participants",participantsDataFetcher);
    return RuntimeWiring.newRuntimeWiring()
        .type("Query",
            typeWiring -> typeWiring
                    .dataFetchers(map)
        ).fieldVisibility(NoIntrospectionGraphqlFieldVisibility.NO_INTROSPECTION_FIELD_VISIBILITY)
        .build();
  }

  public ExecutionResult executeGraphQL(String query) {
    return graphQL.execute(query);
  }

}