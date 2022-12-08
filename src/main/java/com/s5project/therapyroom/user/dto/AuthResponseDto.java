package com.s5project.therapyroom.user.dto;

import com.s5project.therapyroom.user.domain.User;
import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";
    private UserResponse currentUser;

    public AuthResponseDto(String accessToken, UserResponse currentUser){

        this.accessToken = accessToken;
        this.currentUser = currentUser;
    }

}
