package com.gasmap.app;


import com.gasmap.app.entity.Fuel;
import com.gasmap.app.entity.Gas;
import com.gasmap.app.service.GasService;
import com.gasmap.app.service.fuelService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class updateFuelTest {


    @Autowired
    GasService service;

    @Autowired
    fuelService fservice;

    Gas g1 = new Gas();
    Fuel f;

    @Before
    public void createData(){

        g1.setId_gas(0);
        g1.setLatitude_gas(41.1);
        g1.setLongitude_gas(-0.9);
        g1.setStreet_gas("Av updateFuelTest n1");
        g1.setName_gas("Gas1");

        g1.newFuel(f);

        g1 = service.addGas(g1);

        f = new Fuel("Fuel1", 1.1, g1.getId_gas());
        fservice.addFuel(f);

        g1.newFuel(f);
        service.updateGas(g1);

    }


    @Test
    public void Test1(){

        Set<Fuel> fuels1 = service.getById(g1.getId_gas()).getFuels_gas();

        f = new Fuel("Fuel1",0.1,g1.getId_gas());

        //It won't work, price is too low
        String response = service.updateFuel(g1.getLatitude_gas(),g1.getLongitude_gas(),f.getPrice_fuel(),f.getId_fuel());

        System.out.println("No: " + response);

        f = new Fuel("Fuel1",2.0,g1.getId_gas());

        //It won't work, price is too high
        response = service.updateFuel(g1.getLatitude_gas(),g1.getLongitude_gas(),f.getPrice_fuel(),f.getId_fuel());

        System.out.println("No: " + response);

        f = new Fuel("Fuel1",1.05,g1.getId_gas());

        //It won't work, haven't passed 1 day since last time it was changed
        response = service.updateFuel(g1.getLatitude_gas(),g1.getLongitude_gas(),f.getPrice_fuel(),f.getId_fuel());

        System.out.println("No: " + response);

        //It will work, we are changing "tomorrow"
        response = service.updateFuelDepenInjection(g1.getLatitude_gas(),g1.getLongitude_gas(),
                                        f.getPrice_fuel(),f.getId_fuel(),LocalDate.now().plusDays(1));

        System.out.println("Yes: " + response);

        Set<Fuel> fuels2 = service.getById(g1.getId_gas()).getFuels_gas();

        System.out.println("Unchanged Fuel (price is 1.1): ");

        for(Fuel ff : fuels1){
            System.out.println(ff.toString());
        }

        System.out.println("Changed Fuel (price is 1.05): ");

        for(Fuel ff : fuels2){
            System.out.println(ff.toString());
        }

    }

    @After
    public void deleteData(){
        service.deleteGas(g1);
        fservice.deleteFuel(f);
    }

}
