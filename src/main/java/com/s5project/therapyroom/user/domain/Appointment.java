package com.s5project.therapyroom.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="appointment_id")
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name="patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id" ,nullable = false)
    private Doctor doctor;

    @Column(name="notes")
    private String notes;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Temporal(TemporalType.DATE)
    @Column(name="date")
    private Date date;

}
