package com.pawfriendz.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/hello/{name}")
    public String sayHi(@PathVariable String name){
        return "hello "+name;
    }
}
