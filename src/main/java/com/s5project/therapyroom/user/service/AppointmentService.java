package com.s5project.therapyroom.user.service;

import com.s5project.therapyroom.user.domain.Appointment;
import com.s5project.therapyroom.user.domain.Doctor;
import com.s5project.therapyroom.user.domain.Patient;
import com.s5project.therapyroom.user.dto.UpdateAppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AppointmentService {

    Page<Appointment> getAppointmentsForDoctor (Long doctorId, Pageable pageable) throws Exception;
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointment(Long appointmentId, Long doctorId, UpdateAppointmentRequest appointmentRequestChanges) throws Exception;
    Page<Appointment> getPatientAppointments(Long patientId, Pageable pageable) throws Exception;
}
