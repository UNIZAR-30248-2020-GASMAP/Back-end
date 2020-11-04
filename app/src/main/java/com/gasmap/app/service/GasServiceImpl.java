package com.gasmap.app.service;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.entity.Fuel;
import com.gasmap.app.repository.FuelRepository;
import com.gasmap.app.repository.GasRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GasServiceImpl implements GasService {

    @Autowired
    GasRepository repository;

    @Autowired
    FuelRepository fuel_repository;

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
    public Gas getById(int i){
        return repository.findById(i);
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

    @Override
    public boolean updateGas(Gas g){
        try{
            repository.save(g);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteGas(Gas g) {
        try{
            repository.delete(g);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public String updateFuel(Double lat, Double lon, Double price, String fuel) {
        try{
            Gas g = repository.findByLatLon(lat,lon);
            Fuel oldFuel = fuel_repository.findFuelByIdAndGas(fuel,g.getId_gas());
            if(oldFuel == null){
                return "No se ha encontrado el combustible";
            }
            if(price * 0.95 > oldFuel.getPrice_fuel() || price * 1.05 < oldFuel.getPrice_fuel()){
                return "Mensaje antibandalismo";
            }
            LocalDate oFuel = LocalDate.parse(oldFuel.getChange_fuel());
            if(!oFuel.isBefore(LocalDate.now())){
                return "No han pasado 24h desde el ultimo cambio";
            }
            oldFuel.setChange_fuel(LocalDate.now().toString());
            oldFuel.setPrice_fuel(price);
            fuel_repository.save(oldFuel);
            return "Se ha cambiado correctamente";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "No se ha podido resolver la peticion";
    }


    @Override
    public String updateFuelDepenInjection(Double lat, Double lon, Double price, String fuel, LocalDate ld) {
        try{
            Gas g = repository.findByLatLon(lat,lon);
            Fuel oldFuel = fuel_repository.findFuelByIdAndGas(fuel,g.getId_gas());
            if(oldFuel == null){
                return "No se ha encontrado el combustible";
            }
            if(price * 0.95 > oldFuel.getPrice_fuel() || price * 1.05 < oldFuel.getPrice_fuel()){
                return "Mensaje antibandalismo";
            }
            LocalDate oFuel = LocalDate.parse(oldFuel.getChange_fuel());
            if(!oFuel.isBefore(ld)){
                return "No han pasado 24h desde el ultimo cambio";
            }
            oldFuel.setChange_fuel(ld.toString());
            oldFuel.setPrice_fuel(price);
            fuel_repository.save(oldFuel);
            return "Se ha cambiado correctamente";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "No se ha podido resolver la peticion";
    }

}
