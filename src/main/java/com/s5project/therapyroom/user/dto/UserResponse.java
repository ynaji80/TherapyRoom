package com.s5project.therapyroom.user.dto;

import com.s5project.therapyroom.user.domain.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
//    private String name;
    private String username;
    private List<String> roles;

    public UserResponse(String username, List<String> roles) {
//        this.name = name;
        this.username = username;
        this.roles = roles;
    }



}
