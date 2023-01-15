package com.s5project.therapyroom.user.service;

import com.s5project.therapyroom.user.domain.Appointment;
import com.s5project.therapyroom.user.domain.Doctor;
import com.s5project.therapyroom.user.domain.Patient;
import com.s5project.therapyroom.user.dto.UpdateAppointmentRequest;
import com.s5project.therapyroom.user.repository.AppointmentRepo;
import com.s5project.therapyroom.user.repository.DoctorRepo;
import com.s5project.therapyroom.user.repository.PatientRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final AppointmentRepo appointmentRepo;
    @Override
    public Page<Appointment> getAppointmentsForDoctor (Long doctorId, Pageable pageable) throws Exception{
        Optional<Doctor> doctor = doctorRepo.findById(doctorId);
        if (doctor.isEmpty()) throw new Exception("No Doctor with such ID");
        return appointmentRepo.getByDoctor(doctor.get(), pageable);
    }
    @Override
    public Appointment createAppointment(Appointment appointment){
        return appointmentRepo.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Long appointmentId, Long doctorId, UpdateAppointmentRequest appointmentRequestChanges) throws Exception{
        Appointment appointment= (Appointment) getAppointmentsForDoctor(doctorId,Pageable.unpaged());
        if(appointmentRequestChanges.getNotes()!=null) appointment.setNotes(appointment.getNotes());
        if(appointmentRequestChanges.getStatus()!=null) appointment.setStatus(appointment.getStatus());
        if(appointmentRequestChanges.getDate()!=null) appointment.setDate((appointment.getDate()));
        appointmentRepo.save(appointment);
        return appointment;
    }
    @Override
    public Page<Appointment> getPatientAppointments(Long patientId, Pageable pageable) throws Exception{
        Optional<Patient> patient=patientRepo.findById((patientId));
        if(patient.isEmpty()) throw new Exception("no patient with this id");
        return appointmentRepo.getByPatient(patient.get(),pageable);
    }
}
