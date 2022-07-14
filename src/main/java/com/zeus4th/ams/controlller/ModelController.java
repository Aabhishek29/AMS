//package com.zeus4th.ams.controlller;
//
//import java.util.Objects;
//import java.util.concurrent.atomic.AtomicLong;
//
//import com.zeus4th.ams.model.User;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class ModelController {
//
//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();
//    private Boolean is_authenticated = false;
//
//    @GetMapping("/users")
//    public User model(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new User(1, String.format(template, name));
//
//    }
//
//    @GetMapping("/auth")
//    public User auth(@RequestParam(value = "name", defaultValue = "user") String name) {
//
//        if (Objects.equals(name, "abhi")){
//            System.out.println("Name value  "+name);
//            is_authenticated = true;
//        }else {
//            is_authenticated = false;
//        }
//
//        return new User(1, String.format(template, name), is_authenticated);
//
//    }
//}
