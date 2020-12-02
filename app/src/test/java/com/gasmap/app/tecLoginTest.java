package com.gasmap.app;


import com.gasmap.app.service.TecService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class tecLoginTest {

    @Autowired
    TecService tservice;


    @Before
    public void createData(){

    }

    @Test
    public void Test(){
        //Try to login with non-existent Tec account
        String email = "email@email.com";
        String pass = "anypass";

        Boolean no = tservice.login(email,pass);
        //Should not be able to login
        assertFalse(no);

        //Try to login with default's Tec account
        email = "tec@tec.com";
        pass = "TecPass";

        Boolean yes = tservice.login(email,pass);
        //Should be able to login
        assertTrue(yes);
    }


    @After
    public void deleteData(){

    }

}
