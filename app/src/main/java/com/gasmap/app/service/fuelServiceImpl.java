package com.gasmap.app.service;

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
}
