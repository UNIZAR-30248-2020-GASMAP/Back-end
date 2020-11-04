package com.gasmap.app.controller;


import com.gasmap.app.service.ManagerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Manager's API operations")
@CrossOrigin(origins = "*")
public class ManagerController {

    @Autowired
    ManagerService mservice;

}
