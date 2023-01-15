package com.s5project.therapyroom.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctor")
public class Doctor{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="doctor_id")
    private Long doctorId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "doctor",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @Column(name="first_name", length = 20)
    private String firstname;

    @Column(name="last_name", length = 20)
    private String lastname;

    @Column (name="phone")
    private String phone;

    @Column (name="address")
    private String address;

    @Column(name="speciality")
    @Enumerated(EnumType.STRING)
    private DoctorSpeciality speciality;

}
