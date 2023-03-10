package com.poc.patient.controller;

import com.poc.patient.exception.PatientDetailsNotFoundException;
import com.poc.patient.model.APIErrorResponse;
import com.poc.patient.model.Patient;
import com.poc.patient.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author HS106406
 * This PatientController class represents as a front controller
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class PatientController {

    @Autowired
    PatientService patientService;

    /**
     * This to fetch all patients information from database
     * @return This returns List of patient
     */
    @GetMapping("/patient")
    public ResponseEntity<List<Patient>> getPatientDetails(){
        log.info("Inside getPatientdetailes of PatientController");
        return new ResponseEntity<>(patientService.getPatientDetails(), HttpStatus.OK);
    }

    /**
     * This to fetch patient details based on patient Id
     * @param patientId It takes Patient Id as path variable
     * @return This returns patient details of the shared patient Id
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Patient> getPatientDetailById(@PathVariable Integer patientId){
        log.info("Inside getPatientDetailById of PatientController");
        return new ResponseEntity<>(patientService.getPatientDetailById(patientId), HttpStatus.OK);
    }

    /**
     * This is to add a new patient into system
     * @param patient It takes Patient details in the request body
     * @return This returns patient details after persisting into DB
     */
    @PostMapping("/patient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        log.info("Inside addPatient of PatientController");
        return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.CREATED);
    }

    /**
     * This to update patient details for a patient Id
     * @param patientId It takes Patient Id as path variable
     * @param patient It takes Patient details in the request body
     * @return This returns patient details of the updated patient
     */
    @PutMapping("/patient/{patientId}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer patientId, @RequestBody Patient patient){
        log.info("Inside updatePatient of PatientController");
        return new ResponseEntity<>(patientService.updatePatientById(patientId, patient), HttpStatus.ACCEPTED);
    }

    /**
     * This to remove a patient from database based on patient Id
     * @param patientId It takes Patient Id as path variable
     * @return  This returns successful message if the patient removed successfully from DB
     *          Fail: It throws PatientDetailsNotFoundException
     */
    @DeleteMapping("/patient/{patientId}")
    public ResponseEntity<String> removePatientById(@PathVariable Integer patientId){
        log.info("removePatient of PatientController");
        return new ResponseEntity<>(patientService.removePatientById(patientId), HttpStatus.OK);
    }

    /**
     * This method used to fetch patients detail based on patient name contains match
     * @param patientName
     * @return This returns all the matched patients
     */
    @GetMapping("/patient/name/{patientName}")
    public ResponseEntity<List<Patient>> getPatientsByName(@PathVariable String patientName){
        log.info("Inside getPatientByName of PatientController");
        return new ResponseEntity<>(patientService.getPatientsByName(patientName), HttpStatus.OK);
    }

    /**
     * @param pdnfe It takes PatientDetailsNotFoundException as first parameter
     * @param req It takes HttpServletRequest as second parameter
     * @return It returns ResponseEntity of APIErrorResponse
     */
    @ExceptionHandler(value = PatientDetailsNotFoundException.class)
    public ResponseEntity<APIErrorResponse> patientDetailsNotFoundException(PatientDetailsNotFoundException pdnfe, HttpServletRequest req){
        return new ResponseEntity<>(APIErrorResponse.of(pdnfe.getMessage(), HttpStatus.NOT_FOUND.value(), req.getRequestURI()), HttpStatus.NOT_FOUND);
    }
}
