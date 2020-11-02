package com.gasmap.app.service;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.repository.GasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GasServiceImpl implements GasService {

    @Autowired
    GasRepository repository;

    @Override
    public Gas[] getAllGas() throws Exception {
        return repository.findAllGasStations();
    }

    @Override
    public Gas[] getByDistance(double lat2, double lon2) throws Exception {

        return repository.findByDistance(lat2,lon2);
    }
}
