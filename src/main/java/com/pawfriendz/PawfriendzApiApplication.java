package com.pawfriendz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pawfriendz"})
public class PawfriendzApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PawfriendzApiApplication.class, args);
    }

}
