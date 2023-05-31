package com.sandes.speedyDrive.services;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sandes.speedyDrive.dtos.CarDto;
import com.sandes.speedyDrive.models.CarModel;
import com.sandes.speedyDrive.models.ClientModel;
import com.sandes.speedyDrive.repositores.CarRepository;
import com.sandes.speedyDrive.services.exceptions.EntityNotFoundException;
import com.sandes.speedyDrive.services.exceptions.EntityNotUpdadeOrDeleteException;

import jakarta.validation.Valid;

@Service
public class CarService {

	final CarRepository carRepository;
	
	final ClientService clientService;

	public CarService(CarRepository carRepository, ClientService clientService) {
		super();
		this.carRepository = carRepository;
		this.clientService = clientService;
	}

	public CarModel save(CarModel carModel) {
		return carRepository.save(carModel);
	}

	public Page<CarModel> findAll(Pageable Pageable) {
		return carRepository.findAll(Pageable);
	}

	public CarModel findById(UUID id) {
		return carRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found: "+id));
	}
	
	public void checkClient(CarModel car) {
		if(car.getClient() != null) {
			throw new EntityNotUpdadeOrDeleteException("you cannot create, delete or update a car that already has a customer");
		}
	}

	public void delete(CarModel car) {
		
		carRepository.delete(car);	
	}

	public Page<CarModel> findAllAvaliable(Pageable pageable) {
		return  carRepository.findByclientIsNull(pageable);
	}

	public void transferData(@Valid CarDto carDto, CarModel carModel) {
		BeanUtils.copyProperties(carDto, carModel);
		carModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		
	}

	public void insertClient(CarModel carModel) {
		if(carModel.getClient() !=null) {
			ClientModel client= clientService.findById(carModel.getClient().getId());
			client.getCars().add(carModel);
			clientService.save(client);
		}	
	}
}
