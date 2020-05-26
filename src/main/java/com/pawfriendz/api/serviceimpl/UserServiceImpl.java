package com.pawfriendz.api.serviceimpl;

import com.pawfriendz.api.model.User;
import com.pawfriendz.api.service.UserService;
import com.pawfriendz.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.System.out;

@Service("userService")
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> login(User user) {
List<Optional<User>> existingUsers = new ArrayList();
Optional<User> userToReturn = Optional.empty();
        displayAllRegisteredUsers().forEach(registeredUser -> existingUsers.add(Optional.ofNullable(registeredUser)));
        out.println(existingUsers);
        for (Optional<User> existingUser : existingUsers) {
           if(Objects.equals(user.getPassword(), existingUser.get().getPassword()) && Objects.equals(user.getUsername(), existingUser.get().getUsername())){
               userToReturn= userRepository.findById(existingUser.get().getUserId());
           }
            out.println(userToReturn);
        }


        return userToReturn;

    }

    public List<User> displayAllRegisteredUsers() {
        List<User> listOfUsers = userRepository.findAll();
        logger.info("all the users::  " + listOfUsers);
        return listOfUsers;
    }


}
