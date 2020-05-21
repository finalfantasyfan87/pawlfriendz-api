package com.pawfriendz.api.service;

import com.pawfriendz.api.model.User;

import java.util.List;

public interface UserService {
    public void saveUser(User user);
    public boolean login(User user);
    public List<User> displayAllRegisteredUsers();
}
