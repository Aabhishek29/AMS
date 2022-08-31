package com.zeus4th.ams.controlller;

import com.coxautodev.graphql.tools.SchemaParser;
//import com.zeus4th.ams.services.datafetcher.mutations.UserMutation;
import graphql.ExecutionResult;
import com.zeus4th.ams.services.GraphQlService;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmsGraphQlController {
    @Autowired
    private GraphQlService graphQLService;

    @GetMapping ("/graphql")
    public ResponseEntity<Object> getAllProperty(@RequestBody String query) {
        ExecutionResult execute = graphQLService.executeGraphQL(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

//    @PostMapping("graphql")
//    public GraphQLSchema postAllProperty(@RequestBody String mutation){
//        return SchemaParser.newParser()
//                .file("schema.graphqls")
//                .resolvers(new UserMutation())
//                .build()
//                .makeExecutableSchema();
//    }
}