//package com.zeus4th.ams.resolver;
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
//import com.coxautodev.graphql.tools.GraphQLResolver;
//import com.zeus4th.ams.model.User;
//import com.zeus4th.ams.repository.UserRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class UsersResolvers implements GraphQLQueryResolver {
//
//    private UserRepository userRepository;
//
//    public List<User> allUsers(){
//        return userRepository.findAll();
//    }
//
//}
