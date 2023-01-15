package com.s5project.therapyroom.user.controllers;

import com.s5project.therapyroom.user.domain.Appointment;
import com.s5project.therapyroom.user.domain.Doctor;
import com.s5project.therapyroom.user.domain.Patient;
import com.s5project.therapyroom.user.dto.CreateDoctorRequest;
import com.s5project.therapyroom.user.dto.UpdateDoctorRequest;
import com.s5project.therapyroom.user.service.AppointmentService;
import com.s5project.therapyroom.user.service.DoctorService;
import com.s5project.therapyroom.user.service.UserService;
import com.s5project.therapyroom.user.repository.UserRepo;
import com.s5project.therapyroom.user.security.JWTGenerator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/doctors")
//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "http://10.1.10.72:3000")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final UserService userService;
    private final JWTGenerator jwtGenerator;
    private final UserRepo userRepo;
    private final AppointmentService appointmentService;


    // https://blog.tratif.com/2017/11/23/effective-restful-search-api-in-spring/
    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctors(){
        return new ResponseEntity<>(doctorService.getAllDoctors(),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Doctor> createDoctor(@RequestBody CreateDoctorRequest doctorDto, HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            String cook = Arrays.stream(cookies).map(c -> c.getValue()).collect(Collectors.joining(
//                    ", "));
//            doctor.setUser(userService.getUser(jwtGenerator.getUsernameFromToken(cook)));
//        }
        Doctor doctor = new Doctor();
        doctor.setUser(userRepo.findById(doctorDto.getUserId()).get());
        doctor.setFirstname(doctorDto.getFirstname());
        doctor.setLastname(doctorDto.getLastname());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setAddress(doctorDto.getAddress());
        doctor.setSpeciality(doctorDto.getSpeciality());
        return new ResponseEntity<>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }

    @GetMapping(path="/{doctorId}/appointments")
    public ResponseEntity<Page<Appointment>> getAppointmentsForDoctor  (
            @PathVariable Long doctorId,
            Pageable pageable) throws Exception {
        return new ResponseEntity<>(
                appointmentService.getAppointmentsForDoctor(doctorId,pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{doctorId}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable Long doctorId) throws Exception {
        return new ResponseEntity<>(doctorService.getDoctorById(doctorId), HttpStatus.OK);
    }

    @PatchMapping("/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorRequest body) throws Exception{
        Doctor doctor = doctorService.patchDoctor(doctorId, body);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long doctorId) throws Exception {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok().build();
    }
}
