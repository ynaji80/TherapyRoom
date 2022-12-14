package com.s5project.therapyroom.user.repository;

import com.s5project.therapyroom.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);

}