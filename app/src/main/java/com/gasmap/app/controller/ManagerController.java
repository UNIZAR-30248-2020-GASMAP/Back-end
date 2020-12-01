package com.gasmap.app.controller;


import com.gasmap.app.entity.Gas;
import com.gasmap.app.entity.Manager;
import com.gasmap.app.service.GasService;
import com.gasmap.app.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

@RestController
@Api(value = "Manager's API operations")
@CrossOrigin(origins = "*")
public class ManagerController {

    @Autowired
    ManagerService mservice;
    @Autowired
    GasService gservice;

    @ApiOperation(value = "Operation to log a Manager in", response = Manager.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "email", value = "Manager email"),
            @ApiImplicitParam(name = "password", value = "Manager password")
    })
    @PostMapping(value = "/loginManager", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Manager> login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletResponse response){

        try {

            System.out.println("Correo: " + email);
            System.out.println("Password: " + password);

            Manager res = mservice.loginManager(email,password);

            System.out.println(res.toString());
            ResponseEntity<Manager> r = new ResponseEntity<Manager>(res, HttpStatus.OK);
            return new ResponseEntity<Manager>(res, HttpStatus.OK);
        }catch(Exception e) {
            System.out.println(e);
        }
        Manager manager = new Manager();
        System.out.println(manager.getEmail());
        return new ResponseEntity<Manager>(HttpStatus.BAD_REQUEST);
    }



    @ApiOperation(value = "Get all Managers in the app", response = Manager[].class)
    @GetMapping(value = "/listAllMan", produces = "application/json")
    @ResponseBody
    public Manager[] listAllMan(HttpServletResponse response){
        try {
            Manager[] array = mservice.getAll();
            System.out.println(array.length);
            for (Manager man: array) {
                System.out.println(man.toString());
            }
            return array;
        }catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @ApiOperation(value = "Operation to delete a Manager", response = Boolean.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "email", value = "Manager email"),
            @ApiImplicitParam(name = "password", value = "Manager password"),
            @ApiImplicitParam(name = "passCheck", value = "Manager password confirmation")
    })
    @PostMapping(value = "/deleteManager", produces = "application/json")
    @ResponseBody
    public Boolean deleteManager(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("passCheck") String passCheck,  HttpServletResponse response, BindingResult result){

        try {

            Manager m = mservice.getManagerWithPass(email,password);
            if(password.equals(passCheck) && m.getPass_manager().equals(String.valueOf(password.hashCode()))){
                return mservice.deleteManager(m);
            }
            else{
                //Pass and confirmation do not coincide or it does not check with the real manager pass
                return false;
            }

        }catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }



    @ApiOperation(value = "Updates a certain Fuel's price, given Gas' ID, without any condition", response = String.class)
    @PostMapping(value = "/updatePriceMan", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> updatePriceMan(@RequestParam("id_gas") int id_gas, @RequestParam("price") double price,
                                              @RequestParam("fuel") String fuel, HttpServletResponse response){
        try{
            String res = gservice.updateFuelMan(id_gas, price, fuel);
            return new ResponseEntity<String>(res, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Cannot resolve operation", HttpStatus.BAD_REQUEST);
    }





}
