package com.s5project.spring.therapyroom.service;

import com.s5project.spring.therapyroom.domain.Role;
import com.s5project.spring.therapyroom.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    boolean userExits(String username);
    List<User> getUsers();
}
