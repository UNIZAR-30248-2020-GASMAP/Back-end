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

    Gas[] g;
    Fuel f[];

    @Before
    public void createData(){

        g = new Gas[5];
        f = new Fuel[4];

        for(int i=0; i<5; i++){
            g[i] = new Gas();
        }


        String name;
        String street;
        String time;
        Set<Fuel> fuels = new HashSet<Fuel>(0);
        Set<String> services = new HashSet<String>(0);
        double lon;
        double lat;

        //First Gas (Pedrola/Zaragoza)
        lat = 41.786183;
        lon = -1.219913;
        name = "First";
        time ="Mon: 7:00-23:00\\n" +
                "Tue: 7:00-23:00\\n" +
                "Wen: 7:00-23:00\\n" +
                "Thu: 7:00-23:00\\n" +
                "Fri: 7:00-23:00\\n" +
                "Sat: 6:00-00:00\\n" +
                "Sun: 6:00-00:00\\n";
        g[0].setName_gas(name);
        street = "Av First n1";
        g[0].setStreet_gas(street);
        g[0].setServices_gas(services);
        g[0].setTime_gas(time);
        g[0].setLongitude_gas(lon);
        g[0].setLatitude_gas(lat);

        g[0] = service.addGas(g[0]);

        //Second Gas (Zaragoza)
        lat = 41.632936;
        lon = -0.918802;
        name = "Second";
        g[1].setName_gas(name);
        street = "Av Second n2";
        g[1].setStreet_gas(street);

        g[1].setTime_gas(time);
//        g2.fuels_gas.put("Fuel1",1.1);
        g[1] = service.addGas(g[1]);
        f[0] = new Fuel("Fuel1",1.1, g[1].getId_gas());
        fservice.addFuel(f[0]);
        fuels.add(f[0]);
        g[1].setFuels_gas(fuels);
        services.add("Service1");
        g[1].setServices_gas(services);
        g[1].setLongitude_gas(lon);
        g[1].setLatitude_gas(lat);


        g[1] = service.updateGas(g[1]);

        //Third Gas (Andalucia)
        lat = 36.316428;
        lon = -5.492767;
        name = "Third";
        g[2].setName_gas(name);
        street = "Av Third n3";
        g[2].setStreet_gas(street);
        g[2].setTime_gas(time);
        g[2] = service.addGas(g[2]);
        f[1] = new Fuel("Fuel2",2.2, g[2].getId_gas());
        fservice.addFuel(f[1]);
        fuels.add(f[1]);
        g[2].setFuels_gas(fuels);
        services.add("Service2");
        g[2].setServices_gas(services);
        g[2].setLongitude_gas(lon);
        g[2].setLatitude_gas(lat);

        g[2] = service.updateGas(g[2]);

        //Fourth Gas (Galicia)
        lat = 43.191129;
        lon = -8.563962;
        name = "Fourth";
        g[3].setName_gas(name);
        street = "Av Fourth n4";
        g[3].setStreet_gas(street);
        g[3].setTime_gas(time);
        g[3] = service.addGas(g[3]);
        f[2] = new Fuel("Fuel3",3.3, g[3].getId_gas());
        fservice.addFuel(f[2]);
        fuels.add(f[2]);
        g[3].setFuels_gas(fuels);
        services.add("Service3");
        g[3].setServices_gas(services);
        g[3].setTime_gas(time);
        g[3].setLongitude_gas(lon);
        g[3].setLatitude_gas(lat);


        g[3] = service.updateGas(g[3]);

        //Fifth Gas (Germany)
        lat = 53.177762;
        lon = 12.199532;
        name = "Fifth";
        g[4].setName_gas(name);
        street = "Av Fifth n5";
        g[4].setStreet_gas(street);
        g[4].setTime_gas(time);
        g[4] = service.addGas(g[4]);
        f[3] = new Fuel("Fuel4",4.4, g[4].getId_gas());
        fservice.addFuel(f[3]);
        fuels.add(f[3]);
        g[4].setFuels_gas(fuels);
        services.add("Service4");
        g[4].setServices_gas(services);
        g[4].setTime_gas(time);
        g[4].setLongitude_gas(lon);
        g[4].setLatitude_gas(lat);

        g[4] = service.updateGas(g[4]);



    }

    @Test
    public void Test1AllGas() {
        try{
            Gas[] g0 = service.getAllGas();
            assertEquals(5, g0.length);
            for(int i=0; i<g0.length; i++){
                assertEquals(g[i], g0[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void TestOneGasStationGetBy(){
        try{
            double lat = 53.177762, lon = 12.199532;
            Gas g1 = service.getByLatAndLon(lat,lon);
            assertEquals(5, g1.getId_gas());
            g1 = service.getByStreet("Av Fourth n4");
            assertEquals(4, g1.getId_gas());
            g1 = service.getById(3);
            assertEquals("Av Third n3", g1.getStreet_gas());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Given some coordinate points within Zaragoza it should
    // produce a result including the first two Gas stations
    // g1(Pedrola) and g2(Zaragoza)
    @Test
    public void TestByDistance(){
        try{
            double lat2 = 41.64690296, lon2 = -0.91231156;
            Gas[] g2 = service.getByDistance(lat2,lon2);
            assertEquals(2, g2.length);
            assertEquals(1, g2[0].getId_gas());
            assertEquals(2, g2[1].getId_gas());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestById(){
        try{
            int id_gas = 2;
            Gas g2 = service.getById(id_gas);
            assertEquals(2, g2.getId_gas());
            assertEquals("Second", g2.getName_gas());


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void TesUpdateGas(){
        int id_gas = 2;
        String new_name = "New Name";
        String new_time = "Mon: 8:00-23:00\\n" +
                "Tue: 7:00-23:00\\n" +
                "Wen: 8:00-23:00\\n" +
                "Thu: 9:00-23:00\\n" +
                "Fri: 10:00-23:00\\n" +
                "Sat: 6:00-00:00\\n" +
                "Sun: 6:00-00:00\\n";

        String[] new_services = {"serviceA", "serviceB"};
        Set<String> expected = new HashSet<String>();

        expected.add(new_services[0]);
        expected.add(new_services[1]);

        service.updateName(id_gas, new_name);
        assertEquals(new_name, service.getById(id_gas).getName_gas());

        service.updateTime(id_gas, new_time);
        assertEquals(new_time, service.getById(id_gas).getTime_gas());

        service.updateServices(id_gas, new_services);
        assertEquals(expected, service.getById(id_gas).getServices_gas());

        // It should not add this service as its already in the set
        service.updateServices(id_gas, new String[]{"serviceA", "serviceB"});
        assertEquals(expected, service.getById(id_gas).getServices_gas());

        // what happens if you remove two of the services already added
        // it should only have service1 and removed serviceA and serviceB added previously
        String[] new_services2 = {"service1"};
        Set<String> expected2 = new HashSet<String>();
        expected2.add("service1");
        service.updateServices(id_gas, new_services2);
        assertEquals(expected2, service.getById(id_gas).getServices_gas());






    }

    @Test
    public void testSplit(){
        String new_services = "[\"duchas\",\"taller\"]";
        System.out.println("Services: " + new_services);
        String[] arrayString = new_services.split(",");
        for(int i=0; i<arrayString.length; i++){
            arrayString[i] = arrayString[i].replace("[","");
            arrayString[i] = arrayString[i].replace("]","");
            arrayString[i] = arrayString[i].replace("\"","");
        }
        for(String s : arrayString){
            System.out.println("String: " + s);
        }
    }

    @After
    public void deleteData(){
        for (Gas gas : g){
            service.deleteGas(gas);
        }
        for (Fuel fuel : f){
            fservice.deleteFuel(fuel);
        }
    }
}
