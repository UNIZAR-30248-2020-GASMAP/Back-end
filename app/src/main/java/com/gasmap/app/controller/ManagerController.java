package com.gasmap.app.controller;


import com.gasmap.app.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ManagerController {

    @Autowired
    ManagerService mservice;

}
