package com.pawfriendz.api.serviceimpl;

import com.pawfriendz.api.model.User;
import com.pawfriendz.api.service.UserService;
import com.pawfriendz.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean login(User user) {
        boolean existingUser;
        Map<String, String> userMap = new HashMap<>();
        List<  Map<String, String>> userMapList = new ArrayList<>();
        displayAllRegisteredUsers().forEach(registeredUser -> {
            userMap.put(registeredUser.getUsername(), registeredUser.getPassword());
            userMapList.add(userMap);
        });
        existingUser = userMapList.stream().anyMatch(userCredentials -> userCredentials.containsKey(user.getUsername()) && userCredentials.containsValue(user.getPassword()));
        return existingUser;

    }

    public List<User> displayAllRegisteredUsers() {
        List<User> listOfUsers = userRepository.findAll();
        logger.info("all the users::  " + listOfUsers);
        return listOfUsers;
    }
}
