package com.s5project.therapyroom.user.controllers;

import com.s5project.therapyroom.user.domain.Appointment;
import com.s5project.therapyroom.user.domain.Patient;
import com.s5project.therapyroom.user.domain.User;
import com.s5project.therapyroom.user.dto.CreatePatientRequest;
import com.s5project.therapyroom.user.dto.UpdatePatientRequest;
import com.s5project.therapyroom.user.security.JWTGenerator;
import com.s5project.therapyroom.user.service.AppointmentService;
import com.s5project.therapyroom.user.service.PatientService;
import com.s5project.therapyroom.user.service.UserService;
import com.s5project.therapyroom.user.repository.UserRepo;
import com.s5project.therapyroom.user.specs.PatientSpec;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "http://10.1.10.72:3000")
@RequiredArgsConstructor
@Slf4j
public class PatientController {

    private final PatientService patientService;
    private final UserService userService;
    private final UserRepo userRepo;
    private final AppointmentService appointmentService;
    private final JWTGenerator jwtGenerator;

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients(){
        return new ResponseEntity<>(patientService.getAllpatients(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@RequestBody CreatePatientRequest patientDto,
                                                 HttpServletRequest request){
        log.info("this is the patient {}", patientDto);
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            String cook = Arrays.stream(cookies).map(c -> c.getValue()).collect(Collectors.joining(
//                    ", "));
//            patient.setUser(userService.getUser(jwtGenerator.getUsernameFromToken(cook)));
//        }
        Patient patient = new Patient();
        patient.setUser(userRepo.findById(patientDto.getUserId()).get());
        patient.setFirstname(patientDto.getFirstname());
        patient.setLastname(patientDto.getLastname());
        patient.setAge(patientDto.getAge());
        patient.setGender(patientDto.getGender());
        patient.setPhone(patientDto.getPhone());
        patient.setAddress(patientDto.getAddress());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        log.info("this is the patient {}", patient);

        return new ResponseEntity<>(patientService.createPatient(patient),HttpStatus.CREATED);

    }
    @GetMapping(path="/{patientId}/appointments")
    public ResponseEntity<Page<Appointment>> getPatientAppointments(@PathVariable Long patientId,
                                                                    Pageable pageable)throws Exception {
        return new ResponseEntity<>(
                appointmentService.getPatientAppointments(patientId,pageable),HttpStatus.OK
        );
    }
    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long patientId) throws Exception{
        return new ResponseEntity<>(patientService.getPatientById(patientId),HttpStatus.OK);
    }

    @PatchMapping("/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long patientId,
                                                 @RequestBody UpdatePatientRequest body) throws Exception{
        Patient patient=patientService.updatePatient(patientId,body);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> removePatient(@PathVariable Long patientId) throws Exception{
        patientService.deletePatient(patientId);
        return ResponseEntity.ok().build();
    }

}
