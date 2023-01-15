package com.s5project.therapyroom.user.service;

import com.s5project.therapyroom.user.domain.Patient;
import com.s5project.therapyroom.user.domain.Role;
import com.s5project.therapyroom.user.domain.User;
import com.s5project.therapyroom.user.dto.UpdatePatientRequest;
import com.s5project.therapyroom.user.repository.PatientRepo;
import com.s5project.therapyroom.user.repository.UserRepo;
import com.s5project.therapyroom.user.specs.PatientSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService{
    private final PatientRepo patientRepo;
    private final UserRepo userRepo;
    @Override
    public List<Patient> getAllpatients(){
        return patientRepo.findAll();
    }
    @Override
    public Patient getPatientById(Long patientId) throws Exception {
        Optional<Patient> patient=patientRepo.findById(patientId);
        if(patient.isEmpty()){
            throw new Exception("No patient with such id");
        }
        return patient.get();
    }
//    @Override
//    public void addUserToPatient(Long userId, Long patientId) {
//        User user = userRepo.findByUserId(userId);
//        Patient patient = patientRepo.findById(patientId).get();
//        patient.setUser(user);;
//    }

    @Override
    public Patient createPatient(Patient patient){
        return patientRepo.save(patient);
    }
    @Override
    public void deletePatient(Long patientId) throws Exception{
        Patient patient=getPatientById(patientId);
        patientRepo.delete(patient);
    }
    @Override
    public Patient updatePatient(Long patientId, UpdatePatientRequest patientRequestChanges) throws Exception{
        Patient patient=getPatientById(patientId);

        if(patientRequestChanges.getPhone()!=null){
            patient.setPhone(patientRequestChanges.getPhone());
        }
        if(patientRequestChanges.getAddress()!=null){
            patient.setAddress(patientRequestChanges.getAddress());
        }
        patientRepo.save(patient);
        return patient;
    }

}
