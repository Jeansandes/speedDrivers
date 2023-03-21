package com.sandes.speedyDrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpeedyDriveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeedyDriveApplication.class, args);
	}


	@GetMapping("/")
	public String index(){
		return "Olá Mundo!";
	}
}
