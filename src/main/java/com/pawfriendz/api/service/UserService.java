package com.pawfriendz.api.service;

import com.pawfriendz.api.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void saveUser(User user);
    public Optional<User> login(User user);
    public List<User> displayAllRegisteredUsers();
}
