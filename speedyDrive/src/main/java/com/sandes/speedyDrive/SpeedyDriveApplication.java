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

	/*@Override
	public void run(String... args) throws Exception {
		ClientModel cl1 = new ClientModel(null,"Sandro Bjj","06636274352","cohabiano x");
		ClientModel cl2 = new ClientModel(null,"Jean Bjj","111111111111","anil 2");
		
		CarModel cr1 = new CarModel(null,"X5","BMW",cl1);
		CarModel cr2 = new CarModel(null,"gol","FIAT",cl2);
		CarModel cr3 = new CarModel(null,"COROLLA","TOYOTA",cl1);
		CarModel cr4 = new CarModel(null,"A4","AUID",cl2);
		
		cl1.getCars().addAll(Arrays.asList(cr1,cr3));
		cl2.getCars().addAll(Arrays.asList(cr2,cr4));
		
		clientRepository.save(cl1);
		clientRepository.save(cl2);
		
		carRepository.save(cr1);
		carRepository.save(cr2);
		carRepository.save(cr3);
		carRepository.save(cr4);*/


		
		
		

}
