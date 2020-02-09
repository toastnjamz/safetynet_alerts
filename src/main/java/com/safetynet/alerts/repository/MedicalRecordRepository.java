package com.safetynet.alerts.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.domain.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    private List<MedicalRecord> medicalRecordList;

    @Autowired
    public MedicalRecordRepository() {
        this.medicalRecordList = new ArrayList<>();
    }

    public List<MedicalRecord> findAll() {
        return medicalRecordList;
    }

    public MedicalRecord findMedicalRecord(String firstName, String lastName) {
        for (MedicalRecord medical : medicalRecordList) {
            if (medical.getFirstName().equals(firstName) && medical.getLastName().equals(lastName)) {
                return medical;
            }
        }
        return null;
    }

    public MedicalRecord createMedicalRecord(MedicalRecord medical) {
        medicalRecordList.add(medical);
        return medical;
    }

    public void updateMedicalRecord(MedicalRecord medical) {
        MedicalRecord updateMedical = findMedicalRecord(medical.getFirstName(), medical.getLastName());
        updateMedical.setBirthdate(medical.getBirthdate());
        updateMedical.setMedications(medical.getMedications());
        updateMedical.setAllergies(medical.getAllergies());
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord deleteMedical = findMedicalRecord(firstName, lastName);
        medicalRecordList.remove(deleteMedical);
    }
}

