package com.garage.service;

import com.garage.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers ();

    User findUserByName(String name);

    void saveUser(User user);
}