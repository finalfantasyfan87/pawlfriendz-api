package com.pawfriendz.api.controller;

import com.pawfriendz.api.model.User;
import com.pawfriendz.api.service.UserService;
import com.pawfriendz.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping("/register")
    public ResponseEntity<User> registerUser( @ModelAttribute UserDTO userDTO) throws Exception {
        User user = null;
        if (userDTO != null) {
            user = new User(userDTO.getFirstName(),userDTO.getLastName(), userDTO.getEmail(),userDTO.getPassword(),userDTO.getUsername(),userDTO.getPhoneNumber(),userDTO.getFavoriteDog(),userDTO.getProfilePic());
            userService.saveUser(user);
            logger.info(String.format("user id %s was saved to the database.", user.getUserId()));
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public  Optional<User> loginUser(@RequestBody UserDTO userDto) {
        User user = new User(userDto.getUsername(), userDto.getPassword());
        Optional<User> registeredUser = userService.login(user);
         System.out.println("User:: "+registeredUser);
        return  registeredUser;
    }

    @GetMapping("/users")
    public List<User> getUsersList() {
        return userService.displayAllRegisteredUsers();
    }
}