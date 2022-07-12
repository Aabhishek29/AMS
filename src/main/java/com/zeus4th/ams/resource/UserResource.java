package com.zeus4th.ams.resource;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/users")
@RestController
public class UserResource {

    @PostMapping
    public void getAllUser(@RequestBody String query){

    }
}
