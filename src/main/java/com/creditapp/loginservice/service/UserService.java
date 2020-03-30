package com.creditapp.loginservice.service;

import com.creditapp.loginservice.client.UserClient;
import com.creditapp.loginservice.domain.UserDto;

import java.util.List;

public class UserService {

    private UserClient userClient = new UserClient();

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserService() {
    }

    public void addUser(UserDto userDto) {
        userClient.saveUser(userDto);
    }

    public List<UserDto> getUsers() {
        return userClient.getUsers();
    }

}
