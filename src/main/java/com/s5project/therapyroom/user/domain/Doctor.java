package com.s5project.therapyroom.user.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor extends User{
    private String major;


}
