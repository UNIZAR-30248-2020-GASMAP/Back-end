package com.gasmap.app.controller;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.service.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
}
