package com.gasmap.app.forms;

public class GasForm {

    String id;

    String name;

    String street;

    String lat;

    String lon;

    public GasForm() {
    }

    public GasForm(String id, String name, String street, String lat, String lon) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
