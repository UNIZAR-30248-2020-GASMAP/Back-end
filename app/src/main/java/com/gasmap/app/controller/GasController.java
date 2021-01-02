package com.gasmap.app.controller;

import com.gasmap.app.entity.Fuel;
import com.gasmap.app.entity.Gas;
import com.gasmap.app.service.GasService;
import com.gasmap.app.service.fuelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
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


    @ApiOperation(value = "Adds a new Fuel to a given Gas' id", response = String.class)
    @PostMapping(value = "/addFuel", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> addFuel(@RequestParam("id_gas") int id_gas, @RequestParam("price") double price,
                                          @RequestParam("fuel") String fuel, HttpServletResponse response){
        try{
            String res = gasService.addFuelToGas(id_gas, price, fuel);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Cannot resolve operation", HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "Updates a certain Fuel's price, given Gas' Lat and Lon", response = String.class)
    @PostMapping(value = "/updatePriceLatLon", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updatePriceLatLon(@RequestParam("lat") double lat, @RequestParam("lon") double lon,
                                               @RequestParam("price") double price, @RequestParam("fuel") String fuel,
                                               HttpServletResponse response){
        try{
            String res = gasService.updateFuelLonLat(lat, lon, price, fuel);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Cannot resolve operation", HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Updates a certain Fuel's price, given Gas' ID", response = String.class)
    @PostMapping(value = "/updatePrice", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updatePrice(@RequestParam("id_gas") int id_gas, @RequestParam("price") double price,
                                              @RequestParam("fuel") String fuel, HttpServletResponse response){
        try{
            String res = gasService.updateFuel(id_gas, price, fuel);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Cannot resolve operation", HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "Returns a certain fuel's price record", response = Double[].class)
    @GetMapping(value = "/getRecordFuel", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Double[]> getRecordFuel(@RequestParam("id_gas") int id_gas, @RequestParam("fuel") String fuel,
                                                    HttpServletResponse response){
        try{
            Double[] res = fuelService.getRecord(fuel, id_gas);
            return new ResponseEntity<Double[]>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
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
    @PostMapping(value = "/updateGasServices")
    @ResponseBody
    public ResponseEntity<String> updateGasServices(@RequestParam("id_gas") int id_gas, @RequestParam("new_services") String new_services,
                                                HttpServletResponse response){
        try{
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
            String res = gasService.updateServices(id_gas,arrayString);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Gas could not be updated", HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "Give all services", response = String[].class)
    @GetMapping(value = "/allServices", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String[]> allServices(HttpServletResponse response){
        try{
            String[] res = gasService.allServices();
            return new ResponseEntity<String[]>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }



    @ApiOperation(value = "Deletes a Fuel", response = String.class)
    @PostMapping(value = "/deleteFuel", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> deleteFuel(@RequestParam("id_gas") int id, @RequestParam("fuel") String fuel,
                                            HttpServletResponse response){
        try{
            String res = gasService.deleteFuel(id, fuel);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Could not delete it", HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "Returns sorted fuels from a Gas Station, from less expensive to most", response = Fuel[].class)
    @PostMapping(value = "/getFuelsSorted", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Fuel[]> getFuelsSorted(@RequestParam("id_gas") int id,
                                             HttpServletResponse response){
        try{
            return new ResponseEntity<>(gasService.getFuelSorted(id),HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @ApiOperation(value = "Returns Gas Stations filtered by Services", response = Gas[].class)
    @PostMapping(value = "/getGasFiltered", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Gas[]> getGasFiltered(@RequestParam("lat") double lat ,@RequestParam("lon") double lon,
                                                @RequestParam("new_services") String filter_services,
                                                HttpServletResponse response){
        try{
            String[] arrayString = filter_services.split(",");
            for(int i=0; i<arrayString.length; i++){
                arrayString[i] = arrayString[i].replace("[","");
                arrayString[i] = arrayString[i].replace("]","");
                arrayString[i] = arrayString[i].replace("\"","");
            }
            Gas[] res = gasService.filteredGas(arrayString, lat, lon);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
        String new_time = "Mon: 08:00-23:00\\n" +
                "Tue: 07:00-23:00\\n" +
                "Wed: 08:00-23:00\\n" +
                "Thu: 09:00-23:00\\n" +
                "Fri: 10:00-23:00\\n" +
                "Sat: 06:00-00:00\\n" +
                "Sun: 06:00-00:00\\n";
        try{

            f1 = new Fuel();
            f1.setPrice_fuel(1.0);
            f1.setId_fuel("Fuel1");
            f1.setId_gas(1);

            f2 = new Fuel();
            f2.setPrice_fuel(2.0);
            f2.setId_fuel("Fuel2");
            f2.setId_gas(1);

            fuelService.addFuel(f1);
            fuelService.addFuel(f2);

            set.add(f1);
            set.add(f2);

            services.add("tarjeta");
            services.add("agua");
            services.add("aire");
            services.add("minusvalidos");
            services.add("duchas");
            services.add("cama");
            services.add("restaurante");
            services.add("taller");


            g = new Gas();
            g.setId_gas(1);
            g.setLatitude_gas(41.651828650165434);
            g.setLongitude_gas(-0.8810700203771564);
            g.setStreet_gas("Gas nº0");
            g.setName_gas("Name nº0");
            g.setFuels_gas(set);
            g.setServices_gas(services);
            g.setTime_gas(new_time);

            gasService.deleteGas(g);
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
