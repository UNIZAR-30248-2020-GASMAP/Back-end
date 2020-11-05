package com.gasmap.app.service;

import com.gasmap.app.entity.Fuel;

public interface fuelService {

    Fuel[] getAll();
    boolean addFuel(Fuel f);
    boolean deleteFuel(Fuel f);
    Fuel findFuelByIdAndGas(String idFuel, int idGas);
}
