package com.s5project.therapyroom.user.repository;


import com.s5project.therapyroom.user.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);

}

