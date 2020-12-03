package com.gasmap.app.service;

import com.gasmap.app.entity.Gas;

import java.time.LocalDate;

public interface GasService {
    // Return all Gas
    public Gas[] getAllGas() throws Exception;

    // Return all Gas which are at 50km or closer
    public Gas[] getByDistance(double lat2, double lon2) throws Exception;

    // Return a Gas given its street name
    public Gas getByStreet(String street);

    // Return a Gas given its latitude and longitude
    public Gas getByLatAndLon(double lat, double lon);

    // Return a Gas given its ID
    public Gas getById(int i);

    // Return all services available
    public String[] allServices();

    // Insert Gas
    public Gas addGas(Gas g);

    // Insert Gas without changing its ID
    public Gas addGasTest(Gas g);

    // Add a Fuel into a certain Gas, return state as String
    public String addFuelToGas(int id, Double price, String fuel);

    // Update an existent Gas
    public Gas updateGas(Gas g);

    // Update an existent Fuel into a given Gas identified with latitude and longitude
    public String updateFuelLonLat(Double lat, Double lon, Double price, String fuel);

    // Update an existent Fuel into a given Gas identified with ID
    public String updateFuel(int id, Double price, String fuel);

    // Update an existent Fuel into a given Gas identified with ID without any restrictions
    public String updateFuelMan(int id, Double price, String fuel);

    // Update an existent Fuel into a given Gas identified with ID, being able to surpass time restriction
    public String updateFuelDepenInjection(Double lat, Double lon, Double price, String fuel, LocalDate ld);

    // Update an existent Gas' time
    public String updateTime(int id, String new_time);

    // Update an existent Gas' name
    public String updateName(int id, String new_name);

    // Update an existent Gas' services
    public String updateServices(int id, String[] servicesArray);

    // Delete an existent Gas
    public boolean deleteGas(Gas g);

    // Delete an existent Fuel into a given Gas identified with ID
    public String deleteFuel(int id, String fuel);
    
}
