package com.safetynet.alerts.controller;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.exception.DuplicateException;
import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.service.MedicalRecordService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
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
        log.info("HTTP GET request received for getAllMedicalRecords");
        return JsonStream.serialize(medicalRecordService.getAllMedicalRecords());
    }

    @GetMapping("/{firstName}/{lastName}")
    public String getMedicalRecordByFirstLastName(@PathVariable("firstName") String firstName,
                                                  @PathVariable("lastName") String lastName) {
        log.info("HTTP GET request received for getMedicalRecordByFirstLastName: {} {}", firstName, lastName);
        try {
            log.info("HTTP GET request received for getMedicalRecordByFirstLastName, SUCCESS");
            return JsonStream.serialize(medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName));
        }
        catch (NotFoundException e) {
            log.error("HTTP GET request received for getMedicalRecordByFirstLastName, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medical Record not found", e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("HTTP POST request received for createMedicalRecord: {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        try {
            log.info("HTTP POST request received for createMedicalRecord, SUCCESS");
            return JsonStream.serialize(medicalRecordService.createMedicalRecord(medicalRecord));
        }
        catch (DuplicateException e) {
            log.error("HTTP POST request received for createMedicalRecord, ERROR");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Medical Record already exists", e);
        }
    }

    @PutMapping("/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord, @PathVariable("firstName") String firstName,
                                    @PathVariable("lastName") String lastName) {
        log.info("HTTP PUT request received for updateMedicalRecord: {} {}", firstName, lastName);
        try {
            log.info("HTTP PUT request received for updateMedicalRecord, SUCCESS");
            medicalRecordService.updateMedicalRecord(medicalRecord);
        }
        catch (NotFoundException e) {
            log.error("HTTP PUT request received for updateMedicalRecord, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medical Record does not exist", e);
        }
    }

    @DeleteMapping("/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMedicalRecord(@PathVariable("firstName") String firstName,
                                    @PathVariable("lastName") String lastName) {
        log.info("HTTP DELETE request received for deleteMedicalRecord: {} {}", firstName, lastName);
        try {
            log.info("HTTP DELETE request received for deleteMedicalRecord, SUCCESS");
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
        }
        catch (NotFoundException e) {
            log.error("HTTP DELETE request received for deleteMedicalRecord, ERROR");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medical Record does not exist", e);
        }
    }
}
