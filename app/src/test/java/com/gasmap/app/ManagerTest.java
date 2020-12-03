package com.gasmap.app;

import com.gasmap.app.AppApplication;
import com.gasmap.app.entity.Fuel;
import com.gasmap.app.entity.Gas;
import com.gasmap.app.entity.Manager;
import com.gasmap.app.service.GasService;
import com.gasmap.app.service.ManagerService;
import com.gasmap.app.service.fuelService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class ManagerTest {
    @Autowired
    GasService gservice;

    @Autowired
    fuelService fservice;

    @Autowired
    ManagerService mservice;

    Gas g1;
    Gas g2;

    Manager m1;
    Manager m2;

    Fuel f1;
    Fuel f2;
    Fuel f3;
    Fuel f4;

    @Before
    public void createData() throws Exception {
        //Declaration of Gas' attributes
        g1 = new Gas();
        g2 = new Gas();

        String gas_name;
        String gas_street;
        Set<Fuel> fuels = new HashSet<Fuel>(0);
        Set<String> services = new HashSet<String>(0);
        double lon;
        double lat;
        int id_g1 = 1;
        int id_g2 = 2;

        String manager_email;
        String manager_name;
        int manager_phone;
        String manager_pass;

        //First Manager
        manager_email = "nacho@nacho.com";
        manager_name = "nacho";
        manager_phone = 639475843;
        manager_pass = "nacho";



        //First Gas (Pedrola/Zaragoza)
        lat = 41.786183;
        lon = -1.219913;
        gas_name = "First";
        g1.setName_gas(gas_name);
        gas_street = "Av First n1";
        g1.setStreet_gas(gas_street);
        g1.setServices_gas(services);
        g1.setLongitude_gas(lon);
        g1.setLatitude_gas(lat);
        g1.setId_gas(id_g1);

        m1 = new Manager(manager_email,manager_name,manager_phone,manager_pass,g1);
        g1.setManager(m1);
        g1.setTime_gas("");

        gservice.addGas(g1);
        mservice.addManager(m1);

        //Second Manager
        manager_email = "victor@victor.com";
        manager_name = "victor";
        manager_phone = 646800129;
        manager_pass = "victor";

        //Second Gas (Zaragoza)
        lat = 41.632936;
        lon = -0.918802;
        gas_name = "Second";
        g2.setName_gas(gas_name);
        gas_street = "Av Second n2";
        g2.setStreet_gas(gas_street);
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

        m2 = new Manager(manager_email,manager_name,manager_phone,manager_pass,g2);
        g2.setManager(m2);
        g2.setTime_gas("");

        gservice.addGas(g2);
        mservice.addManager(m2);
    }

    @Test
    public void Test1() {
        try{
            Gas[] g = gservice.getAllGas();
            Manager mA = mservice.getManager("nacho@nacho.com");
            Manager mB = mservice.getManager("victor@victor.com");
            assertEquals(2, g.length);
            assertEquals(mA, g[0].getManager());
            assertEquals(mB, g[1].getManager());
            assertEquals(mA.getGas(),g[0]);
            assertEquals(mB.getGas(),g[1]);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void invalidEmail(){


        Manager manager = new Manager("a","name",666666666,"pass",null);

        manager = mservice.addManager(m2);


    }

    @Test
    public void TestLogin(){
        try{
            Manager mA = mservice.getManager("nacho@nacho.com");

            //Password is wrong
            Manager res = mservice.loginManager(mA.getEmail(), "victor");

            assertNull(res);

            //Password is wrong
            res = mservice.loginManager(mA.getEmail(), null);

            assertNull(res);

            //Login is correct
            res = mservice.loginManager(mA.getEmail(), "nacho");

            assertEquals(mA,res);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void addManagerTestFails(){
        Manager m = null;
        assertEquals(null, mservice.addManager(m));
    }

    @Test
    public void getAllManagersTest(){
        Manager[] managers = mservice.getAll();
        assertEquals(2, managers.length);
    }

    @Test
    public void getManagerWithPassTest(){
        Manager test = mservice.getManagerWithPass("nacho@nacho.com", "nacho");
        assertEquals(m1, test);
    }

    @Test
    public void LogInNotFoundTest(){
        try{
            Manager test = mservice.loginManager("undefined@undefined.com", "nacho");
            assertEquals(null, test);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void changeNameTest(){
        try{
            boolean result = mservice.changeName("nacho@nacho.com","nacho","nacho2");
            assertEquals(true, result);
            assertEquals("nacho2", mservice.getManager("nacho@nacho.com").getName_manager());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void changeNameFailsTest(){
        try{
            boolean result = mservice.changeName("nacho@nacho.com","nacho3","nacho2");
            assertEquals(false, result);
            assertNotEquals("nacho2", mservice.getManager("nacho@nacho.com").getName_manager());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void changePassTest(){
        try{
            String newPass = "nacho2";
            boolean result = mservice.changePass("nacho@nacho.com","nacho",newPass);
            assertEquals(true, result);

            assertEquals(newPass.hashCode(), Integer.parseInt(mservice.getManager("nacho@nacho.com").getPass_manager()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void changePassFailsTest(){
        try{
            String newPass = "nacho2";
            boolean result = mservice.changePass("nacho@nacho.com","nacho23",newPass);
            assertEquals(false, result);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void deleteFailsTest(){
        try{
            //Manager test = new Manager("a@a.com","a",976123456,"1234567891234",null);
            boolean result = mservice.deleteManager(null);
            assertEquals(false, result);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @After
    public void deleteData() throws Exception {

        gservice.deleteGas(g1);
        gservice.deleteGas(g2);
        mservice.deleteManager(m1);
        mservice.deleteManager(m2);
        fservice.deleteFuel(f1);

    }

}