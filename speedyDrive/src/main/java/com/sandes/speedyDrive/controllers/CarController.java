package com.sandes.speedyDrive.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandes.speedyDrive.dtos.CarDto;
import com.sandes.speedyDrive.models.CarModel;
import com.sandes.speedyDrive.services.CarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarController {

	final CarService carService;

	public CarController(CarService carService) {
		super();
		this.carService = carService;
	}
	
	@PostMapping
	public ResponseEntity<CarModel> saveCar(@RequestBody @Valid CarDto carsdto){
		var carModel = new CarModel();
		BeanUtils.copyProperties(carsdto, carModel);
		carModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(carModel));
	}
	
	@GetMapping
	public ResponseEntity<Page<CarModel>> getAllCars(@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(carService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarModel> getOneCar(@PathVariable(value= "id")UUID id){
		CarModel carOptional = carService.findById(id).get();
		return ResponseEntity.status(HttpStatus.OK).body(carOptional);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCar(@PathVariable(value= "id")UUID id){
		CarModel carOptional = carService.findById(id).get();
		carService.delete(carOptional);
		return ResponseEntity.status(HttpStatus.OK).body("delete car!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCar(@RequestBody @Valid CarDto carsdto,@PathVariable(value= "id")UUID id){
		CarModel carOptional = carService.findById(id).get();
		
		var carModel = new CarModel();
		BeanUtils.copyProperties(carsdto, carModel);
		carModel.setId(carOptional.getId());
		carModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(carService.save(carModel));
	}
	
	
	
	
	
	

	
}