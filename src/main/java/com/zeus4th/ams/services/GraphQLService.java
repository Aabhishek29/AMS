//package com.zeus4th.ams.services;
//
//import com.zeus4th.ams.services.datafetcher.AllUsersDataFetcher;
//import com.zeus4th.ams.services.datafetcher.UserDataFetcher;
//import graphql.GraphQL;
//import graphql.schema.DataFetcher;
//import graphql.schema.GraphQLSchema;
//import graphql.schema.idl.RuntimeWiring;
//import graphql.schema.idl.SchemaGenerator;
//import graphql.schema.idl.SchemaParser;
//import graphql.schema.idl.TypeDefinitionRegistry;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.core.io.Resource;
//
//import javax.annotation.PostConstruct;
//import java.io.File;
//import java.io.IOException;
//
//@Service
//public class GraphQLService {
//
//    @Value("classpath:users.graphql")
//    Resource resource;
//
//    private GraphQL graphQl;
//    @Autowired
//    private AllUsersDataFetcher allUsersDataFetcher;
//    @Autowired
//    private UserDataFetcher userDataFetcher;
//
//    @PostConstruct
//    private void loadSchema() throws IOException{
//        //this line helps to get Schema
//
//
//        File schemafile = resource.getFile();
//
//        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemafile);
//        RuntimeWiring wiring = buildRunTimeWiring();
//        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry,wiring);
//        graphQl = GraphQL.newGraphQL(schema).build();
//    }
//
//    private RuntimeWiring buildRunTimeWiring() {
//        return RuntimeWiring.newRuntimeWiring()
//                .type("Query",typeWiring->
//                    typeWiring.dataFetcher("allUsers",allUsersDataFetcher)
//                            .dataFetcher("user",userDataFetcher)
//                )
//                .build();
//    }
//
//    public GraphQL getGraphQl() {
//        return graphQl;
//    }
//}
