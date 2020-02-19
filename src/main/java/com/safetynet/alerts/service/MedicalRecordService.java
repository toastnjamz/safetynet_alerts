package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.exception.DuplicateException;
import com.safetynet.alerts.exception.NotFoundException;
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

    public MedicalRecord getMedicalRecordByFirstLastName(String firstName, String lastName) throws NotFoundException {
        if (medicalRecordRepository.findMedicalRecord(firstName, lastName) == null) {
            throw new NotFoundException();
        }
        return medicalRecordRepository.findMedicalRecord(firstName, lastName);
    }

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) throws DuplicateException {
        for (MedicalRecord medicalInList : medicalRecordRepository.findAll()) {
            if (medicalInList.equals(medicalRecord)) {
                throw new DuplicateException();
            }
        }
        return medicalRecordRepository.createMedicalRecord(medicalRecord);
    }

    public void updateMedicalRecord(MedicalRecord medicalRecord) throws NotFoundException {
        if (medicalRecordRepository.findMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName()) == null) {
            throw new NotFoundException();
        }
        medicalRecordRepository.updateMedicalRecord(medicalRecord);
    }

    public void deleteMedicalRecord(String firstName, String lastName) throws NotFoundException {
        if (medicalRecordRepository.findMedicalRecord(firstName, lastName) == null) {
            throw new NotFoundException();
        }
        medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
    }
}
