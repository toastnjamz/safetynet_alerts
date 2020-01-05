package com.safetynet.alerts.domain;

import java.util.HashSet;
import java.util.Set;

public class FireStation {
	
	private Set<String> addresses = new HashSet<String>();
	private String stationNo;
	
	public FireStation(String stationNo) {
		this.stationNo = stationNo;
	}

	public Set<String> getAddresses() {
		return addresses;
	}

	public void addAddress(String address) {
		addresses.add(address);
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}
}