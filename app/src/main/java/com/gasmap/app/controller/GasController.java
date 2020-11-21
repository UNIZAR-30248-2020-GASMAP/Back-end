package com.gasmap.app.controller;

import com.gasmap.app.entity.Fuel;
import com.gasmap.app.entity.Gas;
import com.gasmap.app.service.GasService;
import com.gasmap.app.service.fuelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@RestController
@Api(value = "Gas' API operations")
@CrossOrigin(origins = "*")
public class GasController {
    @Autowired
    GasService gasService;

    @Autowired
    fuelService fuelService;


    @ApiOperation(value = "Get all Gas in the app", response = Gas[].class)
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

    @ApiOperation(value = "Get all Gas inside a certain radius", response = Gas[].class)
    @GetMapping(value = "/listByDistance", produces = "application/json")
    @ResponseBody
    public Gas[] listByDistance(@RequestParam("lat") double lat2, @RequestParam("lon") double lon2,  HttpServletResponse response){
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

    @ApiOperation(value = "Get Gas by id", response = Gas[].class)
    @GetMapping(value = "/listById", produces = "application/json")
    @ResponseBody
    public Gas listById(@RequestParam("id_gas") Integer id_gas,  HttpServletResponse response){
        try {

            Gas g = gasService.getById(id_gas);

            return g;
        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @ApiOperation(value = "Updates a certain Fuel's price", response = String.class)
    @PostMapping(value = "/updatePrice", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updatePrice(@RequestParam("lat") double lat, @RequestParam("lon") double lon,
                                               @RequestParam("price") double price, @RequestParam("fuel") String fuel,
                                               HttpServletResponse response){
        try{
            String res = gasService.updateFuel(lat, lon, price, fuel);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("No se ha podido resolver la peticion", HttpStatus.BAD_REQUEST);
    }
    
    @ApiOperation(value = "Updates all gas info", response = String.class)
    @PostMapping(value = "/updateGas", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updateGas(@RequestParam("gas") Gas g,
                                              HttpServletResponse response){
        try{
            Gas res = gasService.updateGas(g);
            return new ResponseEntity<String>(res.toString(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Gas could not be updated", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Updates gas name", response = String.class)
    @PostMapping(value = "/updateGasName", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updateGasName(@RequestParam("id_gas") int id_gas, @RequestParam("new_name") String new_name,
                                              HttpServletResponse response){
        try{
            String res = gasService.updateName(id_gas,new_name);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Gas could not be updated", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Updates gas time table", response = String.class)
    @PostMapping(value = "/updateGasTime", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updateGasTime(@RequestParam("id_gas") int id_gas, @RequestParam("new_time") String new_time,
                                                HttpServletResponse response){
        try{
            String res = gasService.updateTime(id_gas,new_time);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Gas could not be updated", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Updates gas services", response = String.class)
    @PostMapping(value = "/updateGasServices", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updateGasServices(@RequestParam("id_gas") int id_gas, @RequestParam("new_services") String[] new_services,
                                                HttpServletResponse response){
        try{
            String res = gasService.updateServices(id_gas,new_services);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Gas could not be updated", HttpStatus.BAD_REQUEST);
    }
    


    @ApiOperation(value = "Adds a Test data Gas", response = Boolean.class)
    @GetMapping(value = "/addGasTest", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Boolean> addGasTest(HttpServletResponse response){
        Gas g;
        Fuel f1;
        Fuel f2;
        Set<Fuel> set = new HashSet<>(0);
        Set<String> services = new HashSet<>(0);
        try{
            f1 = new Fuel();
            f1.setPrice_fuel(1.0);
            f1.setId_fuel("Fuel1");
            f1.setId_gas(1000);

            f2 = new Fuel();
            f2.setPrice_fuel(2.0);
            f2.setId_fuel("Fuel2");
            f2.setId_gas(1000);

            fuelService.addFuel(f1);
            fuelService.addFuel(f2);

            set.add(f1);
            set.add(f2);

            services.add("Service1");
            services.add("Service2");
            services.add("Service3");

            g = new Gas();
            g.setId_gas(1000);
            g.setLatitude_gas(41.632936);
            g.setLongitude_gas(0.918802);
            g.setStreet_gas("Test street");
            g.setName_gas("Test name");
            g.setFuels_gas(set);
            g.setServices_gas(services);

            g = gasService.addGasTest(g);
            if(g != null){
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
    }

}
