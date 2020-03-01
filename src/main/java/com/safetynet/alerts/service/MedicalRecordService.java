package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord getMedicalRecordByFirstLastName(String firstName, String lastName) {
        return medicalRecordRepository.findMedicalRecord(firstName, lastName);
    }

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        for (MedicalRecord medicalInList : medicalRecordRepository.findAll()) {
            if (medicalInList.equals(medicalRecord)) {
                return null;
            }
        }
        return medicalRecordRepository.createMedicalRecord(medicalRecord);
    }

    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        for (MedicalRecord recordInList : medicalRecordRepository.findAll()) {
            if (recordInList.equals(medicalRecord)) {
                medicalRecordRepository.updateMedicalRecord(medicalRecord);
            }
        }
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        if (medicalRecordRepository.findMedicalRecord(firstName, lastName) != null) {
            medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
        }
    }
}
