package com.gasmap.app.service;

import com.gasmap.app.entity.Fuel;

public interface fuelService {

    // Returns an array with all the fuels stored in the DB
    Fuel[] getAll();

    // Given an object Fuel
    // Returns true after adding the fuel to the DB
    // otherwise it returns false
    boolean addFuel(Fuel f);

    // Given an object Fuel
    // Returns true after deleting the fuel from the DB
    // otherwise it returns false
    boolean deleteFuel(Fuel f);

    // Given an Sting that represents the id of a certain fuel and
    // an int the represents the id of a gas station
    // Returns that fuel object that is associated with that gas station
    // otherwise it returns null
    Fuel findFuelByIdAndGas(String idFuel, int idGas);

    // Given an Sting that represents the id of a certain fuel and
    // an int the represents the id of a gas station
    // Returns an array with the last five prices of that certain fuel in
    // that gas station
    Double[] getRecord(String idFuel, int idGas);
}
