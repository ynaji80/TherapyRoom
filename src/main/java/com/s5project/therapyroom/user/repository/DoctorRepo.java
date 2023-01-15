package com.s5project.therapyroom.user.repository;

import com.s5project.therapyroom.user.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long>, JpaSpecificationExecutor {
}