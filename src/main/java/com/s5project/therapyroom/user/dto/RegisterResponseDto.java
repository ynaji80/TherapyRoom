package com.s5project.therapyroom.user.dto;

import com.s5project.therapyroom.user.domain.UserType;
import lombok.Data;

@Data
public class RegisterResponseDto {
    private Long userId;
    private String username;
    private UserType type;
    private String message;

}
