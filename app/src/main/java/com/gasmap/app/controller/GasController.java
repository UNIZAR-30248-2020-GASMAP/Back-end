package com.gasmap.app.controller;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.service.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class GasController {
    @Autowired
    GasService gasService;

    @GetMapping(value = "/listAllGas", produces = "application/json")
    @ResponseBody
    public Gas[] listAllGas(HttpServletResponse response){
        try {
            Gas[] array = gasService.getAllGas();

            for (Gas gasStation: array) {
                System.out.println(gasStation.toString());
            }
            return array;
        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @GetMapping(value = "/prueba", produces = "application/json")
    @ResponseBody
    public void easyTest(HttpServletResponse response){
        System.out.println("There we go");
        Gas g1 = new Gas();
        String name;
        String street;
        Map<String,Double> fuels = new HashMap<String,Double>(0);
        Set<String> services = new HashSet<String>(0);
        double lon;
        double lat;

        try{

            lon = 41.786183;
            lat = -1.219913;
            name = "First";
            g1.setName_gas(name);
            street = "Av First n1";
            g1.setStreet_gas(street);
            g1.setServices_gas(services);
            g1.setLongitude_gas(lon);
            g1.setLatitude_gas(lat);

            gasService.addGas(g1);
            System.out.println("OK");

            Gas[] all = gasService.getAllGas();
            for(Gas g : all){
                System.out.print(g.toString());
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/listByDistance", produces = "application/json")
    @ResponseBody
    public Gas[] listByDistance(@RequestParam("lat2") double lat2, @RequestParam("lon2") double lon2,  HttpServletResponse response){
        try {
            System.out.println("Latitud: " + lat2);
            System.out.println("Longitud: " + lon2);
            
            Gas[] array = gasService.getByDistance(lat2, lon2);


            for (Gas gasStation: array) {
                System.out.println(gasStation.toString());
            }
            return array;
        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
