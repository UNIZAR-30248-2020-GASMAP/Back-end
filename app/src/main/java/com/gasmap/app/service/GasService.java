package com.gasmap.app.service;

import com.gasmap.app.entity.Gas;

import java.time.LocalDate;

public interface GasService {
    public Gas[] getAllGas() throws Exception;

    public Gas[] getByDistance(double lat2, double lon2) throws Exception;

    public Gas addGas(Gas g);

    public boolean updateGas(Gas g);

    public boolean deleteGas(Gas g);

    public Gas getById(int i);

    public String updateFuel(Double lat, Double lon, Double price, String fuel);

    public String updateFuelDepenInjection(Double lat, Double lon, Double price, String fuel, LocalDate ld);
}