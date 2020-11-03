package com.gasmap.app.service;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.repository.GasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class GasServiceImpl implements GasService {

    @Autowired
    GasRepository repository;


    @Autowired
    public GasServiceImpl(GasRepository repository) {
        this.repository = repository;
    }


    @Override
    public Gas[] getAllGas() throws Exception {
        return repository.findAllGasStations();
    }

    @Override
    public Gas[] getByDistance(double lat2, double lon2) throws Exception {

        return repository.findByDistance(lat2,lon2);
    }

    @Override
    public Gas addGas(Gas g){
        try{
            long n = repository.count();
            g.setId_gas((int) (long) (n+1));
            return repository.save(g);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
