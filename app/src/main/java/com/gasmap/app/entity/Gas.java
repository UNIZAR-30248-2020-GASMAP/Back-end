package com.gasmap.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

//Entity Gas Station with its attributes and relationships.
@Entity
@Table(name = "ZZGas")
@SequenceGenerator(name="ids", initialValue=1, allocationSize=200)
public class Gas implements Serializable {

    //Database's id
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ids")
    @Column(name = "id_gas", nullable = false, length = 50)
    int id_gas;

    //Gas' name
    @Column(name = "name_gas", nullable = false, length = 50)
    String name_gas;

    //Gas' street
    @Column(name = "street_gas", nullable = false, length = 50)
    String street_gas;

    //Gas' latitude and longitude
    @Column(name = "latitude_gas", nullable = false, length = 50)
    double latitude_gas;
    @Column(name = "longitude_gas", nullable = false, length = 50)
    double longitude_gas;

    //List of services this gas provides
    @ElementCollection
    private Set<String> services_gas = new HashSet<String>(0);


    //List of fuels this gas provides and its prices
    @ElementCollection
    private Map<String,Double> fuels_gas = new HashMap<String, Double>(0);

    public Gas() {
    }


    //Self-made functions

    //Add service to gas
    public boolean addService(String service){
        if(!this.services_gas.contains(service)){
            return this.services_gas.add(service);
        }
        return false;
    }

    //Delete service from gas
    public boolean deleteService(String service){

        if(!this.services_gas.contains(service)){
            return this.services_gas.remove(service);
        }
        return true;
    }

    //Add new fuel to this gas' list
    public boolean newFuel(String name, Double price){
        return this.fuels_gas.putIfAbsent(name, price) != null;
    }

    //Remove fuel from this gas' list
    public boolean removeFuel(String name){
        if(this.fuels_gas.containsKey(name)){
            return this.fuels_gas.remove(name) != null;
        }
        return true;
    }

    //Update fuel's price from this gas' list (if the fuel doesn't exist, it's created)
    public boolean updateFuelPrice(String name, Double newPrice){
        return this.fuels_gas.put(name, newPrice) != null;
    }

    //Generated functions
    public Gas(String street_gas, Set<String> services_gas) {
        this.street_gas = street_gas;
        this.services_gas = services_gas;
    }

    public int getId_gas() {
        return id_gas;
    }

    public String getName_gas(){ return name_gas; }

    public String getStreet_gas() {
        return street_gas;
    }

    public double getLatitude_gas(){ return latitude_gas; }

    public double getLongitude_gas(){ return longitude_gas; }

    public Set<String> getServices_gas() {
        return services_gas;
    }

    public Map<String, Double> getFuels_gas() {
        return fuels_gas;
    }

    public void setId_gas(int id_gas) {
        this.id_gas = id_gas;
    }

    public void setName_gas(String name_gas){ this.name_gas = name_gas; }

    public void setStreet_gas(String street_gas) {
        this.street_gas = street_gas;
    }

    public void setLatitude_gas(double latitude_gas){ this.latitude_gas = latitude_gas; }

    public void setLongitude_gas(double longitude_gas){ this.longitude_gas = longitude_gas; }

    public void setServices_gas(Set<String> services_gas) {
        this.services_gas = services_gas;
    }

    public void setFuels_gas(Map<String, Double> fuels_gas) {
        this.fuels_gas = fuels_gas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gas gas = (Gas) o;
        return id_gas == gas.id_gas &&
                name_gas == gas.name_gas &&
                street_gas.equals(gas.street_gas) &&
                latitude_gas == gas.latitude_gas &&
                longitude_gas == gas.longitude_gas &&
                Objects.equals(services_gas, gas.services_gas) &&
                Objects.equals(fuels_gas, gas.fuels_gas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_gas, street_gas, services_gas, fuels_gas);
    }

    @Override
    public String toString() {
        return "Gas{" +
                "id_gas=" + id_gas +
                ", name_gas=" + name_gas +
                ", street_gas='" + street_gas +
                "\', latitude_gas="+ latitude_gas +
                ", longitude_gas=" + longitude_gas +
                ", services_gas=" + services_gas +
               // ", fuels_gas=" + fuels_gas +
                '}';
    }



}
