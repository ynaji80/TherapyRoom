package com.s5project.therapyroom.user.repository;

import com.s5project.therapyroom.user.domain.Appointment;
import com.s5project.therapyroom.user.domain.Doctor;
import com.s5project.therapyroom.user.domain.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    Page<Appointment> getByDoctor(Doctor doctor, Pageable pageable);

    Page<Appointment> getByPatient(Patient patient, Pageable pageable);
}
