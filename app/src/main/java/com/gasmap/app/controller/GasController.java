package com.gasmap.app.controller;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.service.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @PostMapping()
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
}
