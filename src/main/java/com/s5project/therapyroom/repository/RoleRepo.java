package com.s5project.therapyroom.repository;


import com.s5project.therapyroom.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);

}

