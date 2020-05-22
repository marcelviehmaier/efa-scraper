package de.hspf.efabwscraper;

public class RoutePart {
    private String originDatePlanned;
    private String originDateActual;
    private String destinationDatePlanned;
    private String destinationDateActual;
    private String originPlace;
    private String destinationPlace;
    private String transportationType;
    private String transportationName;

    public String getOriginDatePlanned() {
        return originDatePlanned;
    }

    public void setOriginDatePlanned(String originDatePlanned) {
        this.originDatePlanned = originDatePlanned;
    }

    public String getOriginDateActual() {
        return originDateActual;
    }

    public void setOriginDateActual(String originDateActual) {
        this.originDateActual = originDateActual;
    }

    public String getDestinationDatePlanned() {
        return destinationDatePlanned;
    }

    public void setDestinationDatePlanned(String destinationDatePlanned) {
        this.destinationDatePlanned = destinationDatePlanned;
    }

    public String getDestinationDateActual() {
        return destinationDateActual;
    }

    public void setDestinationDateActual(String destinationDateActual) {
        this.destinationDateActual = destinationDateActual;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public String getTransportationName() {
        return transportationName;
    }

    public void setTransportationName(String transportationName) {
        this.transportationName = transportationName;
    }
}
