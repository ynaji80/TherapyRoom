package com.s5project.therapyroom.user.service;

import com.s5project.therapyroom.user.domain.Doctor;
import com.s5project.therapyroom.user.dto.UpdateDoctorRequest;
import com.s5project.therapyroom.user.repository.DoctorRepo;
import com.s5project.therapyroom.user.specs.DoctorSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepo doctorRepo;

    @Override
    public List<Doctor> getAllDoctors(){
        return doctorRepo.findAll();
    }
    @Override
    public Doctor getDoctorById(Long doctorId) throws Exception{
        Optional<Doctor> doctor = doctorRepo.findById(doctorId);
        if (doctor.isEmpty()){
            throw new Exception("No Doctor with ID "+doctorId);
        }
        return doctor.get();
    }
    @Override
    public Doctor createDoctor(Doctor doctor){
        return doctorRepo.save(doctor);
    }
    @Override
    public void deleteDoctor(Long doctorId) throws Exception {
        Doctor doctor = getDoctorById(doctorId);
        doctorRepo.delete(doctor);
    }
    @Override
    public Doctor patchDoctor(Long doctorId, UpdateDoctorRequest doctorChanges) throws Exception{
        Doctor doctor = getDoctorById(doctorId);
        if (doctorChanges.getDoctorSpeciality() != null ) doctor.setSpeciality(doctorChanges.getDoctorSpeciality());
        if (doctorChanges.getAddress() != null ) doctor.setAddress(doctorChanges.getAddress());
        if (doctorChanges.getPhone() != null ) doctor.setPhone(doctorChanges.getPhone());
        doctorRepo.save(doctor);
        return doctor;
    }
}
