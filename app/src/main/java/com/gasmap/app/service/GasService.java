package com.gasmap.app.service;

import com.gasmap.app.entity.Gas;

public interface GasService {
    public Gas[] getAllGas () throws Exception;
    public Gas[] getByDistance (double lat2, double lon2) throws Exception;
    public Gas addGas(Gas g);
    public boolean updateGas(Gas g);
    public Gas getById(int i);
    public Gas getByStreet(String street);
    public Gas getByLatAndLon(double lat, double lon);
}
