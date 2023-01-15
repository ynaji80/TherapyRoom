package com.s5project.therapyroom.user.service;

import com.s5project.therapyroom.user.domain.Role;
import com.s5project.therapyroom.user.domain.User;
import com.s5project.therapyroom.user.repository.RoleRepo;
import com.s5project.therapyroom.user.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the db", user.getUsername());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the db", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Assigning role {} to user {}",roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {} from the database",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public boolean userExits(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetch all users in the database");
        return userRepo.findAll();
    }
}

