package com.gasmap.app.service;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.entity.Fuel;
import com.gasmap.app.repository.FuelRepository;
import com.gasmap.app.repository.GasRepository;
import com.gasmap.app.service.fuelService;
import net.bytebuddy.asm.Advice;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GasServiceImpl implements GasService {

    @Autowired
    GasRepository repository;

    @Autowired
    FuelRepository fuel_repository;

    @Autowired
    fuelService fService;

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
    public Gas[] getByMaxDistance(double lat2, double lon2, double range) throws Exception {

        return repository.findByMaxDistance(lat2,lon2,range);
    }

    @Override
    public Gas getById(int i){
        return repository.findById(i);
    }

    @Override
    public String addFuelToGas(int id, Double price, String fuel) {
        try{
            Gas g = repository.findById(id);
            Fuel f = new Fuel(fuel,price,id);
            fService.addFuel(f);
            g.fuels_gas.add(f);
            repository.save(g);
            return "Fuel added!";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Could not add fuel to that Gas";
    }


    @Override
    public Gas getByStreet(String street) {
        return repository.findByStreet(street);
    }

    @Override
    public Gas getByLatAndLon(double lat, double lon) {
        return repository.findByLatAndLon(lat,lon);
    }

    @Override
    public String[] allServices() {
        Gas g = repository.findById(1);
        return g.getServices_gas().toArray(new String[0]);
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
    public Gas addGasTest(Gas g) {
        try{
            return repository.save(g);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Gas updateGas(Gas g){
        try{
            return repository.save(g);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
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
    public String updateFuelLonLat(Double lat, Double lon, Double price, String fuel) {
        try{
            Gas g = repository.findByLatLon(lat,lon);
            Fuel oldFuel = fuel_repository.findFuelByIdAndGas(fuel,g.getId_gas());
            if(oldFuel == null){
                return addFuelToGas(g.getId_gas(),price,fuel);
            }
            if(price.equals(oldFuel.getPrice_fuel())){
                return "Changed correctly";
            }
            if(price * 0.95 > oldFuel.getPrice_fuel() || price * 1.05 < oldFuel.getPrice_fuel()){
                return "Cannot change to that price";
            }
            LocalDate oFuel = LocalDate.parse(oldFuel.getChange_fuel());
            if(!oFuel.isBefore(LocalDate.now())){
                return "Cannot change until tomorrow";
            }
            oldFuel.addNewPrice(price);
            oldFuel.setChange_fuel(LocalDate.now().toString());
            oldFuel.setPrice_fuel(price);
            fuel_repository.save(oldFuel);
            return "Changed correctly";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Cannot resolve operation";
    }

    @Override
    public String updateFuel(int id, Double price, String fuel) {
        try{
            Gas g = repository.findById(id);
            if(g == null){
                return "Gas not found";
            }
            Fuel oldFuel = fuel_repository.findFuelByIdAndGas(fuel,g.getId_gas());
            if(oldFuel == null){
                return addFuelToGas(g.getId_gas(),price,fuel);
            }
            if(price.equals(oldFuel.getPrice_fuel())){
                return "Changed correctly";
            }
            if(price * 0.95 > oldFuel.getPrice_fuel() || price * 1.05 < oldFuel.getPrice_fuel()){
                return "Cannot change to that price";
            }
            LocalDate oFuel = LocalDate.parse(oldFuel.getChange_fuel());
            if(!oFuel.isBefore(LocalDate.now())){
                return "Cannot change until tomorrow";
            }
            oldFuel.addNewPrice(price);
            oldFuel.setChange_fuel(LocalDate.now().toString());
            oldFuel.setPrice_fuel(price);
            fuel_repository.save(oldFuel);
            return "Changed correctly";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Cannot resolve operation";
    }

    @Override
    public String updateFuelMan(int id, Double price, String fuel) {
        try{
            Gas g = repository.findById(id);
            Fuel oldFuel = fuel_repository.findFuelByIdAndGas(fuel,g.getId_gas());
            if(oldFuel == null){
                return addFuelToGas(g.getId_gas(),price,fuel);
            }
            oldFuel.addNewPrice(price);
            oldFuel.setChange_fuel(LocalDate.now().toString());
            oldFuel.setPrice_fuel(price);
            fuel_repository.save(oldFuel);
            return "Changed correctly";

        }catch(Exception e){
            e.printStackTrace();
        }
        return "Cannot resolve operation";
    }

    @Override
    public String deleteFuel(int id, String fuel) {
        try{
            Fuel f = fuel_repository.findFuelByIdAndGas(fuel,id);
            fuel_repository.delete(f);
            return "Deleted correctly";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Could not delete it";
    }


    @Override
    public String updateFuelDepenInjection(Double lat, Double lon, Double price, String fuel, LocalDate ld) {
        try{
            Gas g = repository.findByLatLon(lat,lon);
            Fuel oldFuel = fuel_repository.findFuelByIdAndGas(fuel,g.getId_gas());
            if(oldFuel == null){
                return "Fuel not found";
            }
            if(price.equals(oldFuel.getPrice_fuel())){
                return "Changed correctly";
            }
            if(price * 0.95 > oldFuel.getPrice_fuel() || price * 1.05 < oldFuel.getPrice_fuel()){
                return "Cannot change to that price";
            }
            LocalDate oFuel = LocalDate.parse(oldFuel.getChange_fuel());
            if(!oFuel.isBefore(ld)){
                return "Cannot change until tomorrow";
            }
            oldFuel.addNewPrice(price);
            oldFuel.setChange_fuel(ld.toString());
            oldFuel.setPrice_fuel(price);
            fuel_repository.save(oldFuel);
            return "Changed correctly";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Cannot resolve operation";
    }

    @Override
    public String updateTime(int id, String new_time) {
        try{
            Gas g = repository.findById(id);
            g.setTime_gas(new_time);
            repository.save(g);
            return "Changed correctly";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Cannot resolve operation";
    }

    @Override
    public String updateName(int id, String new_name) {
        try{
            Gas g = repository.findById(id);
            g.setName_gas(new_name);
            repository.save(g);
            return "Changed correctly";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Cannot resolve operation";
    }

    @Override
    public String updateServices(int id, String[] servicesArray) {
        try{
            Gas g = repository.findById(id);

            Set<String> newSet = new HashSet<>(0);
            newSet.addAll(Arrays.asList(servicesArray));
            g.setServices_gas(newSet);

            repository.save(g);
            return "Changed correctly";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Cannot resolve operation";
    }

    @Override
    public Fuel[] getFuelSorted(int id){
        try{
            List<Fuel> list = new ArrayList<>(repository.findById(id).getFuels_gas());
            Collections.sort(list);
            return list.toArray(new Fuel[0]);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Gas[] filteredGas(String[] services, double lat, double lon){
        List<Gas> gasFiltered = new ArrayList<>(0);
        Gas[] gases = repository.findByDistance(lat, lon);
        boolean Ok = true;
        Set<String> setServices;
        for(Gas g : gases){
            for(String s : services){
                setServices = g.getServices_gas();
                if (!setServices.contains(s) && !s.isEmpty()) {
                    Ok = false;
                    break;
                }
            }
            if(Ok){
                gasFiltered.add(g);
            }
            Ok = true;
        }
        return gasFiltered.toArray(new Gas[0]);
    }
}
