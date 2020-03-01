package com.safetynet.alerts.domain;

import java.util.List;
import java.util.Objects;

public class Household {

    private String address;

    private List<Person> formattedPersonList;;

    public Household(String address, List<Person> formattedPersonList) {
        this.address = address;
        this.formattedPersonList = formattedPersonList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Person> getFormattedPersonList() {
        return formattedPersonList;
    }

    public void setFormattedPersonList(List<Person> formattedPersonList) {
        this.formattedPersonList = formattedPersonList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Household household = (Household) o;
        return address.equals(household.address) &&
                formattedPersonList.equals(household.formattedPersonList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, formattedPersonList);
    }
}
