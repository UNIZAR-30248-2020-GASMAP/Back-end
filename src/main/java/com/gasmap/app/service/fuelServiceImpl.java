package com.gasmap.app.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.gasmap.app.entity.Fuel;
import com.gasmap.app.repository.FuelRepository;
import com.gasmap.app.repository.GasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class fuelServiceImpl implements fuelService {

    @Autowired
    FuelRepository frepository;

    @Autowired
    public fuelServiceImpl(FuelRepository repository) {
        this.frepository = repository;
    }

    @Override
    public Fuel[] getAll() {
        return frepository.findAllFuels();
    }

    @Override
    public boolean addFuel(Fuel f) {
        try{
            f.addNewPrice(f.getPrice_fuel());
            frepository.save(f);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteFuel(Fuel f) {
        try{
            frepository.delete(f);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Fuel findFuelByIdAndGas(String idFuel, int idGas){
        try{
            return frepository.findFuelByIdAndGas(idFuel, idGas);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Double[] getRecord(String idFuel, int idGas) {
        try{
            Fuel f = frepository.findFuelByIdAndGas(idFuel,idGas);
            return f.getLast_prices().toArray(new Double[0]);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
