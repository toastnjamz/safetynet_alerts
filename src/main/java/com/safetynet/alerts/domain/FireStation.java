package com.safetynet.alerts.domain;

import java.util.Objects;

public class FireStation {

    private String address;
    private String stationNo;

    public FireStation(String address, String stationNo) {
        this.address = address;
        this.stationNo = stationNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress() {
        this.address = address;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        FireStation fireStation = (FireStation) obj;
        return (fireStation.address.equals(this.address) && (fireStation.stationNo.equals(this.stationNo)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, stationNo);
    }
}