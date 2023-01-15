package com.s5project.therapyroom.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="patient")
public class Patient{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="patient_id")
    private Long patientId;

    @OneToMany(
            mappedBy = "patient",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="first_name", length = 20)
    private String firstname;

    @Column(name="last_name", length = 20)
    private String lastname;

    @Column(name="age")
    private Integer age;

    @Column (name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column (name="phone")
    private String phone;

    @Column (name="address")
    private String address;


    @Temporal(TemporalType.DATE)
    @Column(name="date_of_birth")
    private Date dateOfBirth;

}

