package com.s5project.spring.therapyroom.service;

import com.s5project.spring.therapyroom.domain.Role;
import com.s5project.spring.therapyroom.domain.User;
import com.s5project.spring.therapyroom.repository.RoleRepo;
import com.s5project.spring.therapyroom.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        log.info("Fetch user {}",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public boolean userExits(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetch all users");
        return userRepo.findAll();
    }
}

