package com.poc.patient.repository;

import com.poc.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author HS106406
 * This PatientRepository interface represents as a patient repository
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
