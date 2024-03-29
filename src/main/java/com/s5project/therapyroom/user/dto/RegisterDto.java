package com.s5project.therapyroom.user.dto;

import com.s5project.therapyroom.user.domain.UserType;
import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private UserType type;
}
