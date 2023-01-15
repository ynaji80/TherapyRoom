package com.s5project.therapyroom.user.controllers;

import com.s5project.therapyroom.user.domain.Appointment;
import com.s5project.therapyroom.user.domain.Patient;
import com.s5project.therapyroom.user.dto.AppointmentDto;
import com.s5project.therapyroom.user.dto.UpdateAppointmentRequest;
import com.s5project.therapyroom.user.service.AppointmentService;
import com.s5project.therapyroom.user.service.DoctorService;
import com.s5project.therapyroom.user.service.PatientService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/appointments")
//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "http://10.1.10.72:3000")

@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDto appointment) throws Exception{

        Appointment appointment1 = new Appointment();
        appointment1.setNotes(appointment.getNotes());
        appointment1.setStatus(appointment.getStatus());
        appointment1.setPatient(patientService.getPatientById(appointment.getPatientId()));
        appointment1.setDoctor(doctorService.getDoctorById(appointment.getDoctorId()));
        appointment1.setDate(new Date());
        return new ResponseEntity<>(appointmentService.createAppointment(appointment1), HttpStatus.CREATED);
    }
    @PatchMapping("/{doctorId}/{appointmentId}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long appointmentId, @PathVariable Long doctorId
            , @RequestBody UpdateAppointmentRequest body) throws Exception{
        Appointment appointment=appointmentService.updateAppointment(appointmentId,doctorId,body);
        return new ResponseEntity<>(appointment,HttpStatus.OK);
    }
    @GetMapping(path="/{doctorId}/{appointmentId}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long appointmentId,@PathVariable Long doctorId) throws Exception{
        return null;
    }
}
