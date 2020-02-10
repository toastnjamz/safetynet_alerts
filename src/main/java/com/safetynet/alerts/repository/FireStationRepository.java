package com.safetynet.alerts.repository;

import com.safetynet.alerts.domain.FireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FireStationRepository {

    private List<FireStation> fireStationList;

    @Autowired
    public FireStationRepository() {
        this.fireStationList = new ArrayList<>();
    }

    public List<FireStation> findAll() {
        return fireStationList;
    }

    public FireStation findStation(String address) {
        for (FireStation fireStation : fireStationList) {
            if (fireStation.getAddress().equals(address)) {
                return fireStation;
            }
        }
        return null;
    }

    public FireStation createStation(FireStation fireStation) {
        fireStationList.add(fireStation);
        return fireStation;
    }

    public void updateStation(FireStation fireStation) {
        FireStation updateStation = findStation(fireStation.getAddress());
        updateStation.setStationNo(fireStation.getStationNo());
    }

    public void deleteStation(String address) {
        FireStation deleteStation = findStation(address);
        fireStationList.remove(deleteStation);
    }
}
