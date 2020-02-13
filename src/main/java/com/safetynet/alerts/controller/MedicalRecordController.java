package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.exception.DuplicateMedicalRecordException;
import com.safetynet.alerts.exception.MedicalRecordNotFoundException;
import com.safetynet.alerts.service.MedicalRecordService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger log = LoggerFactory.getLogger(MedicalRecordController.class);

    private MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public String getAllMedicalRecords() {
        log.info("GET request made for getAllMedicalRecords");
        return medicalRecordService.getAllMedicalRecords();
    }

    @GetMapping("/{firstName}/{lastName}")
    public String getMedicalRecordByFirstLastName(@PathVariable("firstName") String firstName,
                                                  @PathVariable("lastName") String lastName) {
        log.info("GET request made for getMedicalRecordByFirstLastName: " + firstName + " " + lastName);
        try {
            log.info("GET called for getMedicalRecordByFirstLastName, SUCCESS");
            return medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName);
        }
        catch (MedicalRecordNotFoundException e) {
            log.error("GET called for getMedicalRecordByFirstLastName, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medical Record not found", e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("POST request made for createMedicalRecord: " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        try {
            log.info("POST called for createMedicalRecord, SUCCESS");
            return medicalRecordService.createMedicalRecord(medicalRecord);
        }
        catch (DuplicateMedicalRecordException e) {
            log.error("POST called for createMedicalRecord, ERROR");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Medical Record already exists", e);
        }
    }

    @PutMapping("/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord, @PathVariable("firstName") String firstName,
                                    @PathVariable("lastName") String lastName) {
        log.info("PUT request made for updateMedicalRecord: " + firstName + " " + lastName);
        try {
            log.info("PUT called for updateMedicalRecord, SUCCESS");
            medicalRecordService.updateMedicalRecord(medicalRecord);
        }
        catch (MedicalRecordNotFoundException e) {
            log.error("PUT called for updateMedicalRecord, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medical Record does not exist", e);
        }
    }

    @DeleteMapping("/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMedicalRecord(@PathVariable("firstName") String firstName,
                                    @PathVariable("lastName") String lastName) {
        log.info("DELETE request made for deleteMedicalRecord: " + firstName + " " + lastName);
        try {
            log.info("DELETE called for deleteMedicalRecord, SUCCESS");
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
        }
        catch (MedicalRecordNotFoundException e) {
            log.error("DELETE called for deleteMedicalRecord, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medical Record does not exist", e);
        }
    }
}
