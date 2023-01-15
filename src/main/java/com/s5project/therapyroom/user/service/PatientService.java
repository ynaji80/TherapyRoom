package com.s5project.therapyroom.user.service;

import com.s5project.therapyroom.user.domain.Patient;
import com.s5project.therapyroom.user.dto.UpdatePatientRequest;
import com.s5project.therapyroom.user.specs.PatientSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> getAllpatients();
    Patient getPatientById(Long patientId)  throws Exception ;
    Patient createPatient(Patient patient);
    void deletePatient(Long patientId)  throws Exception ;
    Patient updatePatient(Long patientId, UpdatePatientRequest patientRequestChanges)  throws Exception ;
}
