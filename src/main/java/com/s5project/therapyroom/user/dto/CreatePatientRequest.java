package com.s5project.therapyroom.user.dto;

import com.s5project.therapyroom.user.domain.Gender;
import com.s5project.therapyroom.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class CreatePatientRequest {
    private Long userId;
    private String firstname;
    private String lastname;
    private Integer age;
    private Gender gender;
    private String phone;
    private String address;
    private Date dateOfBirth;
}
