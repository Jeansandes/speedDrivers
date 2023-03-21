package com.sandes.speedyDrive.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

import com.sandes.speedyDrive.dtos.ClientDto;
import com.sandes.speedyDrive.models.ClientModel;
import com.sandes.speedyDrive.services.CarService;
import com.sandes.speedyDrive.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

	final ClientService  clientService;
	
	public ClientController(ClientService clientService, CarService carService) {
		super();
		this.clientService = clientService;
		this.carService = carService;
	}

	@PostMapping
	public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientDto clientsDto){
		var clientModel = new ClientModel();
		BeanUtils.copyProperties(clientsDto, clientModel);
		clientModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneClient(@PathVariable(value= "id") UUID id){
		Optional<ClientModel> clientOptional = clientService.findById(id);
		if(!clientOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(clientOptional.get());
	}
	
	@GetMapping
	public ResponseEntity<Page<ClientModel>> getAllClients(@PageableDefault(page = 0, size = 2, sort = "id",
	direction = Sort.Direction.ASC) Pageable pageable){
		
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(pageable));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable(value= "id") UUID id){
		Optional<ClientModel> clientOptional = clientService.findById(id);
		if(!clientOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id not found!");
		}
		clientService.delete(clientOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("customer deleted successfully!");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateClient(@RequestBody @Valid ClientDto clientsDto, @PathVariable(value= "id") UUID id){
		Optional<ClientModel> clientOptional = clientService.findById(id);
		if(!clientOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id not found!");
		}
		var clientModel = new ClientModel();
		clientModel.setId(clientOptional.get().getId());
		clientModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
	}
	
	
	
	
	
}
