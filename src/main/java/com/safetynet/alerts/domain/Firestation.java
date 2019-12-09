package com.safetynet.alerts.domain;

import java.util.HashSet;
import java.util.Set;

public class Firestation {
	
	private Set<String> addresses = new HashSet<String>();
	private String station;
	
	public Firestation(String station) {
		this.station = station;
	}

	public Set<String> getAddresses() {
		return addresses;
	}

	public void addAddress(String address) {
		addresses.add(address);
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}
}