package com.gasmap.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

//Entity Gas Station with its attributes and relationships.
@Entity
@Table(name = "ZZGas")
public class Gas implements Serializable {

    //Database's id
    @Id
    @Column(name = "id_gas", nullable = false, length = 50)
    Integer id_gas;

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

    //Gas' time table
    @Column(name = "time_gas", nullable = false, length = 200)
    String time_gas;

    //List of services this gas provides
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> services_gas = new HashSet<String>(0);


    //List of fuels this gas provides and its prices
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "fuels_gas", joinColumns = {
            @JoinColumn(name = "id_gas", nullable = false, updatable = false)},
            inverseJoinColumns = { @JoinColumn(name = "id_fuel", nullable = false, updatable = false),
                                    @JoinColumn(name = "id_gas_fuel", table = "Fuel",nullable = false, updatable = false)})
    public Set<Fuel> fuels_gas = new HashSet<Fuel>(0);

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_manager_gas", referencedColumnName = "email")
    private Manager manager;

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
    public boolean newFuel(Fuel f){
        return this.fuels_gas.add(f);
    }

    //Remove fuel from this gas' list
    public boolean removeFuel(Fuel f){
        if(this.fuels_gas.contains(f)){
            return this.fuels_gas.remove(f);
        }
        return true;
    }

    //Update fuel's price from this gas' list (if the fuel doesn't exist, it's created)
    public boolean updateFuelPrice(Fuel f){
        if(this.fuels_gas.contains(f)){
            this.fuels_gas.remove(f);
            return this.fuels_gas.add(f);
        }

        return false;
    }

    //Generated functions
    public Gas(String street_gas, Set<String> services_gas, Manager manager) {
        this.street_gas = street_gas;
        this.services_gas = services_gas;
        this.manager = manager;
    }

    public Gas(Integer id_gas, String name_gas, String street_gas, double latitude_gas, double longitude_gas, String time_gas, Set<String> services_gas, Set<Fuel> fuels_gas, Manager manager) {
        this.id_gas = id_gas;
        this.name_gas = name_gas;
        this.street_gas = street_gas;
        this.latitude_gas = latitude_gas;
        this.longitude_gas = longitude_gas;
        this.time_gas = time_gas;
        this.services_gas = services_gas;
        this.fuels_gas = fuels_gas;
        this.manager = manager;
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

    public String getTime_gas(){ return time_gas; }

    public Set<String> getServices_gas() {
        return services_gas;
    }

    public Set<Fuel> getFuels_gas() {
        return fuels_gas;
    }

    public Manager getManager() { return manager; }

    public void setId_gas(int id_gas) {
        this.id_gas = id_gas;
    }

    public void setName_gas(String name_gas){ this.name_gas = name_gas; }

    public void setStreet_gas(String street_gas) {
        this.street_gas = street_gas;
    }

    public void setLatitude_gas(double latitude_gas){ this.latitude_gas = latitude_gas; }

    public void setLongitude_gas(double longitude_gas){ this.longitude_gas = longitude_gas; }

    public void setTime_gas(String time_gas){ this.time_gas = time_gas; }

    public void setServices_gas(Set<String> services_gas) {
        this.services_gas = services_gas;
    }

    public void setFuels_gas(Set<Fuel> fuels_gas) {
        this.fuels_gas = fuels_gas;
    }

    public void setManager(Manager manager) { this.manager = manager; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gas gas = (Gas) o;
        return id_gas == gas.id_gas &&
                Objects.equals(name_gas, gas.name_gas) &&
                street_gas.equals(gas.street_gas) &&
                latitude_gas == gas.latitude_gas &&
                longitude_gas == gas.longitude_gas &&
                time_gas == gas.time_gas &&
                Objects.equals(services_gas, gas.services_gas) &&
                Objects.equals(fuels_gas, gas.fuels_gas) &&
                Objects.equals(manager, gas.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_gas, street_gas, services_gas, fuels_gas);
    }

    @Override
    public String toString() {
        String stringServices = "[";
        String stringFuels = "";
        for(String s : services_gas){
            stringServices += s + ",";
        }
        stringServices += "]";

        for(Fuel f : fuels_gas){
            stringFuels += f.toString() + ", ";
        }
        stringFuels += "]";

        return "Gas{" +
                "id_gas=" + id_gas +
                ", name_gas=" + name_gas +
                ", street_gas='" + street_gas +
                "\', latitude_gas="+ latitude_gas +
                ", longitude_gas=" + longitude_gas +
                ", time_gas=" + time_gas +
                 "," + stringServices +
                "," + stringFuels +
                '}';
    }



}
