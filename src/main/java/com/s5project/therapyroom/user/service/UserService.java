package com.s5project.therapyroom.user.service;


import com.s5project.therapyroom.user.domain.Role;
import com.s5project.therapyroom.user.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    boolean userExits(String username);
    List<User> getUsers();
}
