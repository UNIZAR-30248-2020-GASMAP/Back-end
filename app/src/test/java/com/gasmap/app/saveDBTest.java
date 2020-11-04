package com.gasmap.app;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import com.gasmap.app.entity.Fuel;
import com.gasmap.app.service.GasService;
import com.gasmap.app.service.fuelService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


import com.gasmap.app.entity.Gas;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class saveDBTest {

    @Autowired
    GasService service;

    @Autowired
    fuelService fservice;

    Gas g1;
    Gas g2;
    Gas g3;
    Gas g4;
    Gas g5;

    Fuel f1;
    Fuel f2;
    Fuel f3;
    Fuel f4;

    @Before
    public void createData(){

        assertNotNull(service);

        //Declaration of Gas' attributes
        g1 = new Gas();
        g2 = new Gas();
        g3 = new Gas();
        g4 = new Gas();
        g5 = new Gas();


        String name;
        String street;
        Set<Fuel> fuels = new HashSet<Fuel>(0);
        Set<String> services = new HashSet<String>(0);
        double lon;
        double lat;
        int id_g1 = 0;
        int id_g2 = 1;
        int id_g3 = 2;
        int id_g4 = 3;
        int id_g5 = 4;

        //First Gas (Pedrola/Zaragoza)
        lat = 41.786183;
        lon = -1.219913;
        name = "First";
        g1.setName_gas(name);
        street = "Av First n1";
        g1.setStreet_gas(street);
        g1.setServices_gas(services);
        g1.setLongitude_gas(lon);
        g1.setLatitude_gas(lat);
        g1.setId_gas(id_g1);

        service.addGas(g1);

        //Second Gas (Zaragoza)
        lat = 41.632936;
        lon = -0.918802;
        name = "Second";
        g2.setName_gas(name);
        street = "Av Second n2";
        g2.setStreet_gas(street);
        g2.setId_gas(id_g2);
//        g2.fuels_gas.put("Fuel1",1.1);
        f1 = new Fuel("Fuel1",1.1, g2.getId_gas());
        fservice.addFuel(f1);
        fuels.add(f1);
        g2.setFuels_gas(fuels);
        services.add("Service1");
        g2.setServices_gas(services);
        g2.setLongitude_gas(lon);
        g2.setLatitude_gas(lat);


        service.addGas(g2);

        //Third Gas (Andalucia)
        lat = 36.316428;
        lon = -5.492767;
        name = "Third";
        g3.setName_gas(name);
        street = "Av Third n3";
        g3.setStreet_gas(street);
        g3.setId_gas(id_g3);
        f2 = new Fuel("Fuel2",2.2, g3.getId_gas());
        fservice.addFuel(f2);
        fuels.add(f2);
        g3.setFuels_gas(fuels);
        services.add("Service2");
        g3.setServices_gas(services);
        g3.setLongitude_gas(lon);
        g3.setLatitude_gas(lat);
        g3.setId_gas(id_g3);

        service.addGas(g3);

        //Fourth Gas (Galicia)
        lat = 43.191129;
        lon = -8.563962;
        name = "Fourth";
        g4.setName_gas(name);
        street = "Av Fourth n4";
        g4.setStreet_gas(street);
        g4.setId_gas(id_g4);
        f3 = new Fuel("Fuel3",3.3, g4.getId_gas());
        fservice.addFuel(f3);
        fuels.add(f3);
        g4.setFuels_gas(fuels);
        services.add("Service3");
        g4.setServices_gas(services);
        g4.setLongitude_gas(lon);
        g4.setLatitude_gas(lat);


        service.addGas(g4);

        //Fifth Gas (Germany)
        lat = 53.177762;
        lon = 12.199532;
        name = "Fifth";
        g5.setName_gas(name);
        street = "Av Fifth n5";
        g5.setStreet_gas(street);
        g5.setId_gas(id_g5);
        f4 = new Fuel("Fuel4",4.4, g5.getId_gas());
        fservice.addFuel(f4);
        fuels.add(f4);
        g5.setFuels_gas(fuels);
        services.add("Service4");
        g5.setServices_gas(services);
        g5.setLongitude_gas(lon);
        g5.setLatitude_gas(lat);

        service.addGas(g5);

    }

    @Test
    public void Test1() {
        try{
            Gas[] g = service.getAllGas();
            for(Gas gas : g){
                System.out.println(gas.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @After
    public void deleteData(){

        service.deleteGas(g1);
        service.deleteGas(g2);
        service.deleteGas(g3);
        service.deleteGas(g4);
        service.deleteGas(g5);
        fservice.deleteFuel(f1);
        fservice.deleteFuel(f2);
        fservice.deleteFuel(f3);
        fservice.deleteFuel(f4);

    }
}
