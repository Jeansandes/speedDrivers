package com.sandes.speedyDrive.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sandes.speedyDrive.models.CarModel;
import com.sandes.speedyDrive.repositores.CarRepository;

@Service
public class CarService {

	final CarRepository carRepositoroy;

	public CarService(CarRepository carRepositoroy) {
		super();
		this.carRepositoroy = carRepositoroy;
	}

	public CarModel save(CarModel carModel) {
		return carRepositoroy.save(carModel);
	}

	public Page<CarModel> findAll(Pageable Pageable) {
		return carRepositoroy.findAll(Pageable);
	}

	public Optional<CarModel> findById(UUID id) {
		return carRepositoroy.findById(id);
	}

	public void delete(CarModel carOptional) {
		carRepositoroy.delete(carOptional);	
	}
}
