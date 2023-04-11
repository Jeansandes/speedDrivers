package com.sandes.speedyDrive.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
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
	public ResponseEntity<Object> saveCar(@RequestBody @Valid CarDto carsdto){
		var carModel = new CarModel();	
		BeanUtils.copyProperties(carsdto, carModel);
		carModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		if(carModel.getClient() !=null) {
			Optional<ClientModel> clientOptional= clientService.findById(carModel.getClient().getId());
			if(!clientOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("client not found!");
			}
			var clientModel = new ClientModel();
			BeanUtils.copyProperties(clientOptional.get(), clientModel);
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
		CarModel carOptional = carService.findById(id).get();
		return ResponseEntity.status(HttpStatus.OK).body(carOptional);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCar(@PathVariable(value= "id")UUID id){
		CarModel carOptional = carService.findById(id).get();
		if(carOptional.getClient() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("you cannot delete or update a car that already has a customer");
		}
		carService.delete(carOptional);
		return ResponseEntity.status(HttpStatus.OK).body("delete car!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCar(@RequestBody @Valid CarDto carsdto,@PathVariable(value= "id")UUID id){
		CarModel carOptional = carService.findById(id).get();
		if(carOptional.getClient() != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("esse carro pertence a um cliente!");
		}
		var carModel = new CarModel();
		BeanUtils.copyProperties(carsdto, carModel);
		carModel.setId(carOptional.getId());
		carModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(carService.save(carModel));
	}
	
	
	
	

	
}
