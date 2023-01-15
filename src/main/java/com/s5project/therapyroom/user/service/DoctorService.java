package com.s5project.therapyroom.user.service;

import com.s5project.therapyroom.user.domain.Doctor;
import com.s5project.therapyroom.user.dto.UpdateDoctorRequest;
import com.s5project.therapyroom.user.specs.DoctorSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getDoctorById(Long doctorId) throws Exception;
    Doctor createDoctor(Doctor doctor);
    void deleteDoctor(Long doctorId) throws Exception;
    Doctor patchDoctor(Long doctorId, UpdateDoctorRequest doctorChanges) throws Exception;
}
