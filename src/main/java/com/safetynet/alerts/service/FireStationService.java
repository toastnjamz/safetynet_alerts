package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.exception.DuplicateException;
import com.safetynet.alerts.exception.NotFoundException;
import com.safetynet.alerts.repository.FireStationRepository;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    private FireStationRepository fireStationRepository;
    private PersonService personService;

    @Autowired
    public FireStationService(FireStationRepository fireStationRepository, PersonService personService) {
        this.fireStationRepository = fireStationRepository;
        this.personService = personService;
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

    //TODO
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

//    //MultiValuedMap is not a supported type in Jsoniter
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

    public HashMap<String, List<String>> getListAdultsAndChildrenByStationNumber(String stationNumber) {
        List<Person> personList = getPersonsByStationNumber(stationNumber);
        HashMap<String, List<String>> personMap = new HashMap<>();

        List<String> firstNamesList = new ArrayList<>();
        for (Person person : personList) {
            firstNamesList.add(person.getFirstName());
        }

        List<String> lastNamesList = new ArrayList<>();
        for (Person person : personList) {
            lastNamesList.add(person.getLastName());
        }

        List<String> addressList = new ArrayList<>();
        for (Person person : personList) {
            addressList.add(person.getAddress());
        }

        List<String> phoneList = new ArrayList<>();
        for (Person person : personList) {
            phoneList.add(person.getPhone());
        }

        for (Person person : personList) {
            personMap.put("firstname", firstNamesList);
            personMap.put("lastname", lastNamesList);
            personMap.put("address", addressList);
            personMap.put("phone", phoneList);
        }

        List<String> numChildrenList = new ArrayList<>();
        numChildrenList.add(personService.getChildrenInList(personList));

        List<String> numAdultsList = new ArrayList<>();
        numAdultsList.add(personService.getAdultsInList(personList));

        personMap.put("Children", numChildrenList);
        personMap.put("Adults", numAdultsList);

        return personMap;
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
