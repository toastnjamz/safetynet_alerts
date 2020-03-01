package com.safetynet.alerts.controller;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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

    //TODO: test
    @GetMapping("/{firstName}/{lastName}")
    public String getMedicalRecordByFirstLastName(@NotNull @PathVariable("firstName") String firstName,
                                                  @NotNull @PathVariable("lastName") String lastName,
                                                  HttpServletResponse response) {
        log.info("HTTP GET request received for getMedicalRecordByFirstLastName: {} {}", firstName, lastName);
        Optional<MedicalRecord> medicalOptional = Optional.ofNullable(medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName));
        if (medicalOptional.isPresent()) {
            log.info("HTTP GET request received for getMedicalRecordByFirstLastName, SUCCESS");
            return JsonStream.serialize(medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName));
        }
        else {
            log.error("HTTP GET request received for getMedicalRecordByFirstLastName, ERROR");
            response.setStatus(404);
            return String.format("Medical record for %s %s does not exist", firstName, lastName);
        }
    }

    //TODO: test
    @PostMapping
    public String createMedicalRecord(@NotNull @RequestBody MedicalRecord medicalRecord,
                                      HttpServletResponse response) {
        log.info("HTTP POST request received for createMedicalRecord: {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        Optional<MedicalRecord> medicalOptional = Optional.ofNullable(medicalRecordService.getMedicalRecordByFirstLastName(medicalRecord.getFirstName(), medicalRecord.getLastName()));
        if (!medicalOptional.isPresent()) {
            log.info("HTTP POST request received for createMedicalRecord, SUCCESS");
            return JsonStream.serialize(medicalRecordService.createMedicalRecord(medicalRecord));
        }
        else {
            log.error("HTTP POST request received for createMedicalRecord, ERROR");
            response.setStatus(409);
            return String.format("Medical record for %s %s already exists", medicalRecord.getFirstName(), medicalRecord.getLastName());
        }
    }

    //TODO: test
    @PutMapping("/{firstName}/{lastName}")
    public void updateMedicalRecord(@NotNull @RequestBody MedicalRecord medicalRecord,
                                    @NotNull @PathVariable("firstName") String firstName,
                                    @NotNull @PathVariable("lastName") String lastName,
                                    HttpServletResponse response) {
        log.info("HTTP PUT request received for updateMedicalRecord: {} {}", firstName, lastName);
        Optional<MedicalRecord> medicalOptional = Optional.ofNullable(medicalRecordService.getMedicalRecordByFirstLastName(medicalRecord.getFirstName(), medicalRecord.getLastName()));
        if (medicalOptional.isPresent()) {
            log.info("HTTP PUT request received for updateMedicalRecord, SUCCESS");
            response.setStatus(200);
            medicalRecordService.updateMedicalRecord(medicalRecord);
        }
        else {
            log.error("HTTP PUT request received for updateMedicalRecord, ERROR");
            response.setStatus(404);
        }
    }

    //TODO: test
    @DeleteMapping("/{firstName}/{lastName}")
    public void deleteMedicalRecord(@NotNull @PathVariable("firstName") String firstName,
                                    @NotNull @PathVariable("lastName") String lastName,
                                    HttpServletResponse response) {
        log.info("HTTP DELETE request received for deleteMedicalRecord: {} {}", firstName, lastName);
        Optional<MedicalRecord> medicalOptional = Optional.ofNullable(medicalRecordService.getMedicalRecordByFirstLastName(firstName, lastName));
        if (medicalOptional.isPresent()) {
            log.info("HTTP DELETE request received for deleteMedicalRecord, SUCCESS");
            response.setStatus(200);
            medicalRecordService.deleteMedicalRecord(firstName, lastName);
        }
        else {
            log.error("HTTP DELETE request received for deleteMedicalRecord, ERROR");
            response.setStatus(404);
        }
    }
}
