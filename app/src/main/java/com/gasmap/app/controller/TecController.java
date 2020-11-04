package com.gasmap.app.controller;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.service.GasService;
import com.gasmap.app.service.ManagerService;
import com.gasmap.app.service.TecService;
import com.gasmap.app.entity.Manager;
import com.gasmap.app.forms.User;
import com.gasmap.app.forms.Man;
import com.gasmap.app.forms.GasForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@CrossOrigin(origins = "*")
public class TecController {

    @Autowired
    TecService tecService;

    @Autowired
    ManagerService manService;

    @Autowired
    GasService gasService;


    @GetMapping("/tecLogin")
    public String blog(Model model){
        model.addAttribute("loginForm", new User());
        return "loginTec";
    }

    @PostMapping("/tecLogin")
    public String loginUser(@Valid @ModelAttribute("loginForm")User user, Model model,
                                HttpServletResponse response){
        try {

            if(!tecService.login(user.getEmail(), user.getPass())){
                response.sendRedirect("/tecLogin");
                return "loginTec";
            }
            ////
            Cookie cookie = new Cookie("correo", user.getEmail());
            //cookie.setSecure(true);
            //Cookie for 1h
            cookie.setMaxAge(60 * 60);
            //response.addCookie(cookie);
            System.out.println("Finishing");
            model.addAttribute("managerForm", new Man());
            response.sendRedirect("/chooseOption");
            return "chooseOption";
        } catch (Exception e) {
            model.addAttribute("formErrorMessage", e.getMessage());
            model.addAttribute("loginForm", user);
        }
        return "loginTec";
    }

    @GetMapping("/chooseOption")
    public String getChooseOption(Model model){
        return "chooseOption";
    }


    @PostMapping("/chooseOption")
    public String postChooseOption(Model model, HttpServletResponse response){
        return "chooseOption";
    }

    @GetMapping("/createGas")
    public String getCreateGas(Model model){
        model.addAttribute("gasForm", new GasForm());
        return "createGas";
    }


    @PostMapping("/createGas")
    public String postCreateGas(@Valid @ModelAttribute("GasForm")GasForm gas, Model model,
                                HttpServletResponse response){

        try{

            Gas g = new Gas();
            g.setName_gas(gas.getName());
            g.setStreet_gas(gas.getStreet());
            g.setLongitude_gas(Double.parseDouble(gas.getLon()));
            g.setLatitude_gas(Double.parseDouble(gas.getLat()));
            g.setId_gas(Integer.parseInt(gas.getId()));
            gasService.addGas(g);

            response.sendRedirect("/chooseOption");
            return "chooseOption";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "chooseOption";
    }


    @GetMapping("/createManager")
    public String getCreateMan(Model model){
        model.addAttribute("managerForm", new Man());
        return "createManager";
    }


    @PostMapping("/createManager")
    public String postCreateMan(@Valid @ModelAttribute("managerForm")Man man, Model model,
                                    HttpServletResponse response){

        try{
            Manager m = new Manager();
            m.setEmail_manager(man.email_manager);
            m.setPass_manager(man.email_manager);
            m.setName_manager(man.name_manager);
            m.setPhone_manager(Integer.parseInt(man.phone_manager));

            Gas g = gasService.getById(Integer.parseInt(man.gas_manager));

            m.setGas(g);

            manService.addManager(m);
            response.sendRedirect("/chooseOption");

            return "chooseOption";

        }catch (Exception e){
            e.printStackTrace();
        }

        return "chooseOption";
    }

}
