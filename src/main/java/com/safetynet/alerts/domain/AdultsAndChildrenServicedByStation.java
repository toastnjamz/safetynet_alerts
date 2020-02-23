package com.safetynet.alerts.domain;

import java.util.List;
import java.util.Objects;

// This is a special class designed to satsify the requirements of the project
// Used for FireStationController method: firestation?stationNumber=<station_number>
public class AdultsAndChildrenServicedByStation {

    private List<String> formattedPersonList;
    private String numberOfAdults;
    private String numberOfChildren;

    public AdultsAndChildrenServicedByStation(List<String> formattedPersonList, String numberOfAdults, String numberOfChildren) {
        this.formattedPersonList = formattedPersonList;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    }

    public List<String> getFormattedPersonList() {
        return formattedPersonList;
    }

    public void setFormattedPersonList(List<String> formattedPersonList) {
        this.formattedPersonList = formattedPersonList;
    }

    public String getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(String numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdultsAndChildrenServicedByStation that = (AdultsAndChildrenServicedByStation) o;
        return formattedPersonList.equals(that.formattedPersonList) &&
                numberOfChildren.equals(that.numberOfChildren) &&
                numberOfAdults.equals(that.numberOfAdults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formattedPersonList, numberOfChildren, numberOfAdults);
    }
}
