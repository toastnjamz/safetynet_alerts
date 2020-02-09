package com.safetynet.alerts.service;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.exception.DuplicateMedicalRecordException;
import com.safetynet.alerts.exception.MedicalRecordNotFoundException;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public String getAllMedicalRecords() {
        return JsonStream.serialize(medicalRecordRepository.findAll());
    }

    public String getMedicalRecordByFirstLastName(String firstName, String lastName) {
        if (medicalRecordRepository.findMedicalRecord(firstName, lastName) == null) {
            throw new MedicalRecordNotFoundException();
        }
        return JsonStream.serialize(medicalRecordRepository.findMedicalRecord(firstName, lastName));
    }

    public String createMedicalRecord(MedicalRecord medicalRecord) throws DuplicateMedicalRecordException {
        for (MedicalRecord medicalInList : medicalRecordRepository.findAll()) {
            if (medicalInList.equals(medicalRecord)) {
                throw new DuplicateMedicalRecordException();
            }
        }
        return JsonStream.serialize(medicalRecordRepository.createMedicalRecord(medicalRecord));
    }

    public void updateMedicalRecord(MedicalRecord medicalRecord) throws MedicalRecordNotFoundException {
        if (medicalRecordRepository.findMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()) == null) {
            throw new MedicalRecordNotFoundException();
        }
        medicalRecordRepository.updateMedicalRecord(medicalRecord);
    }

    public void deleteMedicalRecord(String firstName, String lastName) throws MedicalRecordNotFoundException {
        if (medicalRecordRepository.findMedicalRecord(firstName, lastName) == null) {
            throw new MedicalRecordNotFoundException();
        }
        medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
    }
}
