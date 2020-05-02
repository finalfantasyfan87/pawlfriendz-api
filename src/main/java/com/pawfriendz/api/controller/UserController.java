package com.pawfriendz.api.controller;

import com.pawfriendz.api.model.User;
import com.pawfriendz.api.service.UserService;
import com.pawfriendz.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String sayHi(@Valid @RequestBody UserDTO userDTO
    ) throws NoSuchAlgorithmException {
if(userDTO!=null){
    User user = new User(userDTO.getUserId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), hashPassword(userDTO.getPassword()), userDTO.getUsername());
    userService.saveUser(user);
}


        return "Thank you register, " + userDTO.getUsername() + " you were saved to the collection.";
    }


    public String hashPassword(String text) throws NoSuchAlgorithmException {
        String hashedPw = "";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text.getBytes());
        //Get the hash's bytes
        byte[] bytes = md.digest();
        String sb = IntStream.range(0, bytes.length).mapToObj(i -> Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)).collect(Collectors.joining());
        //Get complete hashed password in hex format
        hashedPw = sb;
        return hashedPw;
    }
}
