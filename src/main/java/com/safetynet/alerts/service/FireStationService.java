package com.safetynet.alerts.service;

import com.jsoniter.output.JsonStream;
import com.safetynet.alerts.domain.FireStation;
import com.safetynet.alerts.exception.DuplicateFireStationException;
import com.safetynet.alerts.exception.FireStationNotFoundException;
import com.safetynet.alerts.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireStationService {

    private FireStationRepository fireStationRepository;

    @Autowired
    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    public String getAllFireStations() {
        return JsonStream.serialize(fireStationRepository.findAll());
    }

    public String getFireStationByAddress(String address) throws FireStationNotFoundException {
        if (fireStationRepository.findStation(address) == null) {
            throw new FireStationNotFoundException();
        }
        return JsonStream.serialize(fireStationRepository.findStation(address));
    }

    public String createFireStation(FireStation fireStation) throws DuplicateFireStationException {
        for (FireStation stationInList : fireStationRepository.findAll()) {
            if (stationInList.equals(fireStation)) {
                throw new DuplicateFireStationException();
            }
        }
        return JsonStream.serialize(fireStationRepository.createStation(fireStation));
    }

    public void updateFireStation(FireStation fireStation) throws FireStationNotFoundException {
        if (fireStationRepository.findStation(fireStation.getAddress()) == null) {
            throw new FireStationNotFoundException();
        }
        fireStationRepository.updateStation(fireStation);
    }

    public void deleteFireStation(String address) throws FireStationNotFoundException {
        if (fireStationRepository.findStation(address) == null) {
            throw new FireStationNotFoundException();
        }
        fireStationRepository.deleteStation(address);
    }
}
