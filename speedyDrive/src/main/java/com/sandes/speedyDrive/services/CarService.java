package com.sandes.speedyDrive.services;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.sandes.speedyDrive.models.CarModel;
import com.sandes.speedyDrive.repositores.CarRepository;
import com.sandes.speedyDrive.services.exceptions.EntityNotFoundException;
import com.sandes.speedyDrive.services.exceptions.EntityNotUpdadeOrDeleteException;

@Service
public class CarService {

	final CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		super();
		this.carRepository = carRepository;
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

}
