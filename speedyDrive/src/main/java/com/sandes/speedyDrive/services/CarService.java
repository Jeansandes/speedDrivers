package com.sandes.speedyDrive.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sandes.speedyDrive.models.CarModel;
import com.sandes.speedyDrive.repositores.CarRepository;

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
	
	public List<CarModel> findAllAvaliable(){
		return carRepository.findByclientIsNull();
	}

	public Optional<CarModel> findById(UUID id) {
		return carRepository.findById(id);
	}

	public void delete(CarModel carOptional) {
		carRepository.delete(carOptional);	
	}

	public void updateClientNull(UUID id) {
		/*carRepository.updateClientNull(id);*/
	}

}
