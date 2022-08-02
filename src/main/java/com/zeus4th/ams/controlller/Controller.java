package com.zeus4th.ams.controlller;

import graphql.ExecutionResult;
import com.zeus4th.ams.services.GraphQlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private GraphQlService graphQLService;

    @GetMapping("/graphql")
    public ResponseEntity<Object> getAllProperty(@RequestBody String query) {
        ExecutionResult execute = graphQLService.executeGraphQL(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}