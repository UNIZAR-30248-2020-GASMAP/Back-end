package com.gasmap.app.service;

import com.gasmap.app.entity.Gas;

public interface GasService {
    public Gas[] getAllGas () throws Exception;
    public Gas[] getByDistance (double lat2, double lon2);
}
