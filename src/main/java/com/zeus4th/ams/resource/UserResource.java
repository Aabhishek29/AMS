package com.zeus4th.ams.resource;


import com.zeus4th.ams.services.GraphQLService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/users")
@RestController
public class UserResource {

    @Autowired
    GraphQLService graphQLService;

    @PostMapping
    public ResponseEntity<Object> getAllUser(@RequestBody String query){
        ExecutionResult executionResult = graphQLService.getGraphQl().execute(query);
        return new ResponseEntity<Object>(executionResult, HttpStatus.OK);
    }
}
