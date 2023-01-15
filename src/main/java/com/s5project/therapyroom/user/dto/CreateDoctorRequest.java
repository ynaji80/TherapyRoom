package com.s5project.therapyroom.user.dto;


import com.s5project.therapyroom.user.domain.DoctorSpeciality;
import com.s5project.therapyroom.user.domain.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class CreateDoctorRequest {
    private Long userId;
    private String firstname;
    private String lastname;
    private String phone;
    private String address;
    private DoctorSpeciality speciality;
}
