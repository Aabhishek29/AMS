package com.zeus4th.ams;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.logging.Logger;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class AmsApplication {
//    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
//
//    @Value("#{'${allowed.origins}'.split(',')}")
//    private List<String> rawOrigins;


    public static void main(String[] args) {
        System.out.println("Started AMS");
        SpringApplication.run(AmsApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                System.out.println("Setting CORS to the service");
//                logger.info("Adding CORS to the service");
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080","https://portal-ams.herokuapp.com",
                                "http://localhost:3000")
                        .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.OPTIONS.name(),"DELETE", "PUT", "PATCH")
                        .allowedHeaders(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE,"Cache-Control")
                        .exposedHeaders(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE,"Cache-Control")
                        .maxAge(4800);
            }
        };
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration config = new CorsConfiguration();
//
//        config.setAllowedOrigins(Arrays.asList("http://localhost:8080", "https://portal-ams.herokuapp.com","http" +
//                "://localhost:3000"));
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
//        config.setAllowCredentials(true);
//        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
//
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//
//        return source;
//    }

//
//    public String[] getOrigin() {
//        int size = rawOrigins.size();
//        String[] originArray = new String[size];
//        return rawOrigins.toArray(originArray);
//    }

}
