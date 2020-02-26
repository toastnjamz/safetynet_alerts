package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.domain.AdultsAndChildrenServicedByStation;
import com.safetynet.alerts.domain.Household;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicateException;
import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    private FireStationRepository fireStationRepository;
    private PersonService personService;
    private MedicalRecordService medicalRecordService;

    @Autowired
    public FireStationService(FireStationRepository fireStationRepository, PersonService personService,
                              MedicalRecordService medicalRecordService) {
        this.fireStationRepository = fireStationRepository;
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
    }

    public List<FireStation> getAllFireStations() {
        return fireStationRepository.findAll();
    }

    public FireStation getFireStationByAddress(String address) throws NotFoundException {
        if (fireStationRepository.findStation(address) == null) {
            throw new NotFoundException();
        }
        return fireStationRepository.findStation(address);
    }

    public FireStation createFireStation(FireStation fireStation) throws DuplicateException {
        for (FireStation stationInList : fireStationRepository.findAll()) {
            if (stationInList.equals(fireStation)) {
                throw new DuplicateException();
            }
        }
        return fireStationRepository.createStation(fireStation);
    }

    public void updateFireStation(FireStation fireStation) throws NotFoundException {
        if (fireStationRepository.findStation(fireStation.getAddress()) == null) {
            throw new NotFoundException();
        }
        fireStationRepository.updateStation(fireStation);
    }

    public void deleteFireStation(String address) throws NotFoundException {
        if (fireStationRepository.findStation(address) == null) {
            throw new NotFoundException();
        }
        fireStationRepository.deleteStation(address);
    }

    //TODO: remove
    public List<String> getAddressesByStationNumber(String stationNumber) {
        List<String> addressList = new ArrayList<>();
        List<FireStation> stationList = fireStationRepository.findAll().stream().filter(f -> f.getStationNo().equals(stationNumber)).collect(Collectors.toList());

        for (FireStation fireStation : stationList) {
//            if (!addressList.contains(fireStation.getAddress())) {
//                addressList.add(fireStation.getAddress());
//            }
            addressList.add(fireStation.getAddress());
        }
        return addressList;
    }

    public List<Person> getPersonsByStationNumber(String stationNumber) {
        List<Person> personList = new ArrayList<>();

        if (fireStationRepository.findAll().stream().anyMatch(f -> f.getStationNo().equals(stationNumber))) {
            List<String> addressList = getAddressesByStationNumber(stationNumber);
            personList = personService.getPersonsAtAddresses(addressList);
        }
        return personList;
    }

    public AdultsAndChildrenServicedByStation getListAdultsAndChildrenByStationNumber(String stationNumber) {
        List<Person> personList = getPersonsByStationNumber(stationNumber);
        List<String> formattedPersonList = new ArrayList<>();
        AdultsAndChildrenServicedByStation adultsAndChildrenServicedByStation;

        for (Person person : personList) {
            formattedPersonList.add(person.getFirstName());
            formattedPersonList.add(person.getLastName());
            formattedPersonList.add(person.getAddress());
            formattedPersonList.add(person.getPhone());
        }

        adultsAndChildrenServicedByStation = new AdultsAndChildrenServicedByStation(formattedPersonList,
                personService.getAdultsInList(personList),
                personService.getChildrenInList(personList));

        return adultsAndChildrenServicedByStation;
    }

    //TODO: remove
//    //Tuples are not supported in Jsoniter (cannot serialize)
//    public Triplet<List<String>, String, String> getListAdultsAndChildrenByStationNumber(String stationNumber) {
//        List<Person> personList = getPersonsByStationNumber(stationNumber);
//        List<String> formattedPersonList = new ArrayList<>();
//
//        for (Person person : personList) {
//            formattedPersonList.add(person.getFirstName());
//            formattedPersonList.add(person.getLastName());
//            formattedPersonList.add(person.getAddress());
//            formattedPersonList.add(person.getPhone());
//        }
//
//        Triplet<List<String>, String, String> personData = new Triplet<List<String>, String, String>(
//                formattedPersonList,
//                "Children: " + personService.getChildrenInList(personList),
//                "Adults: " + personService.getAdultsInList(personList));
//
//        return personData;
//    }

//    //MultiValuedMap is not a supported type in Jsoniter (? not supported)
//    public MultiValuedMap<String, String> getListAdultsAndChildrenByStationNumber(String stationNumber) {
//        List<Person> personList = getPersonsByStationNumber(stationNumber);
//        MultiValuedMap<String, String> personMap = new ArrayListValuedHashMap<>();
//
//        for (Person person : personList) {
//            personMap.put("firstname", person.getFirstName());
//            personMap.put("lastname", person.getLastName());
//            personMap.put("address", person.getAddress());
//            personMap.put("phone", person.getPhone());
//
//        }
//        personMap.put("Children", personService.getChildrenInList(personList));
//        personMap.put("Adults", personService.getAdultsInList(personList));
//
//        return personMap;
//    }

    public HashMap<String, List<Person>> getStationAndPersonsByAddress(String address) {
        HashMap<String, List<Person>> resultsMap = new HashMap<>();
        List<Person> formattedPersonList = new ArrayList<>();

        if (fireStationRepository.findAll().stream().anyMatch(f -> f.getAddress().equals(address))) {
            FireStation fireStation = fireStationRepository.findStation(address);
            List<Person> initialPersonList = getPersonsByStationNumber(fireStation.getStationNo());

            for (Person person : initialPersonList) {
                Person formattedPerson = new Person(person.getFirstName(), person.getLastName(), person.getPhone(),
                        personService.getPersonAge(person.getFirstName(), person.getLastName()),
                        medicalRecordService.getMedicalRecordByFirstLastName(person.getFirstName(), person.getLastName()).getMedications(),
                        medicalRecordService.getMedicalRecordByFirstLastName(person.getFirstName(), person.getLastName()).getAllergies());
                formattedPersonList.add(formattedPerson);
            }
            resultsMap.put(fireStation.getStationNo(), formattedPersonList);
        }
        return resultsMap;
    }

    public List<Household> getHouseholdsByStationList(List<String> stationNumberList) {
        List<Household> householdList = new ArrayList<>();

        for (String stationNumber : stationNumberList) {
            if (fireStationRepository.findAll().stream().anyMatch(f -> f.getStationNo().equals(stationNumber))) {
                List<String> addressList = getAddressesByStationNumber(stationNumber);

                for (String address : addressList) {
                    List<Person> initialPersonList = personService.getPersonsAtAddress(address);
                    List<Person> formattedPersonList = new ArrayList<>();

                    for (Person person : initialPersonList) {
                        Person formattedPerson = new Person (
                            person.getFirstName(),
                            person.getLastName(),
                            person.getPhone(),
                            personService.getPersonAge(person.getFirstName(), person.getLastName()),
                            medicalRecordService.getMedicalRecordByFirstLastName(person.getFirstName(), person.getLastName()).getMedications(),
                            medicalRecordService.getMedicalRecordByFirstLastName(person.getFirstName(), person.getLastName()).getAllergies());
                        formattedPersonList.add(formattedPerson);
                    }
                    Household household = new Household(address, formattedPersonList);
                    householdList.add(household);
                }
            }
        }
        return householdList;
    }

    public List<String> getPhoneNumbersByStationNumber(String stationNumber) {
        List<String> phoneNumbers = new ArrayList<>();

        if (fireStationRepository.findAll().stream().anyMatch(f -> f.getStationNo().equals(stationNumber))) {
            List<Person> personList = getPersonsByStationNumber(stationNumber);
            for (Person personInList : personList) {
                phoneNumbers.add(personInList.getPhone());
            }
        }
        return phoneNumbers;
    }
}
