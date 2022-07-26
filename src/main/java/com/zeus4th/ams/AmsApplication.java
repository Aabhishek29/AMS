package com.zeus4th.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class AmsApplication {

    public static void main(String[] args) {
        System.out.println("Started AMS");
        SpringApplication.run(AmsApplication.class, args);
    }

}
