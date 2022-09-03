//package com.zeus4th.ams.resolvers;
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
//import com.zeus4th.ams.model.User;
//import com.zeus4th.ams.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class UserResolvers implements GraphQLQueryResolver {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public List<User> allUser(String userId){
//        return userRepository.findAll();
//    }
//
//}
