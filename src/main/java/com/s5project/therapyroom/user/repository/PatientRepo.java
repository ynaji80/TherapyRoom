package com.s5project.therapyroom.user.repository;

import com.s5project.therapyroom.user.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long>, JpaSpecificationExecutor {
}
