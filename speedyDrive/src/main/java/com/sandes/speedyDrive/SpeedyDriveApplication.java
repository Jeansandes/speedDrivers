package com.sandes.speedyDrive;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sandes.speedyDrive.models.CarModel;
import com.sandes.speedyDrive.models.ClientModel;
import com.sandes.speedyDrive.repositores.CarRepository;
import com.sandes.speedyDrive.repositores.ClientRepository;

@SpringBootApplication
public class SpeedyDriveApplication {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CarRepository  carRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpeedyDriveApplication.class, args);
	}

	


		
		
		

}
