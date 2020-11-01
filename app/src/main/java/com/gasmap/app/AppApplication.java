package com.gasmap.app;

import com.gasmap.app.entity.Gas;
import com.gasmap.app.service.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {
	public static void main(String[] args) throws Exception {

		SpringApplication.run(AppApplication.class, args);

	}

}
