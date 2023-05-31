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
import com.sandes.speedyDrive.models.ClientModel;
import com.sandes.speedyDrive.services.CarService;
import com.sandes.speedyDrive.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarController {

	final CarService carService;
	final ClientService clientService;

	public CarController(CarService carService,ClientService clientService) {
		super();
		this.carService = carService;
		this.clientService = clientService;
	}
	
	@PostMapping
	public ResponseEntity<CarModel> saveCar(@RequestBody @Valid CarDto carsdto){
		var carModel = new CarModel();	
		BeanUtils.copyProperties(carsdto, carModel);
		carModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		if(carModel.getClient() !=null) {
			ClientModel client= clientService.findById(carModel.getClient().getId());
			var clientModel = new ClientModel();
			BeanUtils.copyProperties(client, clientModel);
			clientModel.getCars().add(carModel);
			clientService.save(clientModel);
			
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(carService.save(carModel));
	}
	
	@GetMapping("/avaliableCars")
	public ResponseEntity<Page<CarModel>> getAllAvaliableCars(@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(carService.findAllAvaliable(pageable));
	}
	
	@GetMapping
	public ResponseEntity<Page<CarModel>> getAllCars(@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(carService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarModel> getOneCar(@PathVariable(value= "id")UUID id){
		CarModel car = carService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(car);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCar(@PathVariable(value= "id")UUID id){
		CarModel car = carService.findById(id);
		carService.checkClient(car);
		carService.delete(car);
		return ResponseEntity.status(HttpStatus.OK).body("delete car!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CarModel> updateCar(@RequestBody @Valid CarDto carsdto,@PathVariable(value= "id")UUID id){
		CarModel car = carService.findById(id);
		carService.checkClient(car);
		var carModel = new CarModel();
		BeanUtils.copyProperties(carsdto, carModel);
		carModel.setId(car.getId());
		carModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(carService.save(carModel));
	}
	
	
	
	

	
}
