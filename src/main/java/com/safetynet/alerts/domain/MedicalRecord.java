package com.safetynet.alerts.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedicalRecord {
	
	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;

	public MedicalRecord(String firstName, String lastName, String birthdate,
			List<String> medications, List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> NewMedications) {
		medications.clear();
		medications.addAll(NewMedications);
	}

	//TODO: Remove unused methods
//	public String getMedication(String medication) {
//		for (String medInList : medications) {
//			if (medInList.equals(medication)) {
//				return medInList;
//			}
//		}
//		return null;
//	}
//
//	public void setMedication(String medication) {
//		medications.add(medication);
//	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> newAllergies) {
		allergies.clear();
		allergies.addAll(newAllergies);
	}

//	public String getAllergy(String allergy) {
//		for (String allergyInList : allergies) {
//			if (allergyInList.equals(allergy)) {
//				return allergyInList;
//			}
//		}
//		return null;
//	}
//
//	public void setAllergy(String allergy) {
//		allergies.add(allergy);
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		MedicalRecord medicalRecord = (MedicalRecord) obj;
		return (medicalRecord.firstName.equals(this.firstName) && (medicalRecord.lastName.equals(this.lastName)));
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}
}
