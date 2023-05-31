package com.sandes.speedyDrive.controllers;

import java.util.UUID;

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
import com.sandes.speedyDrive.services.AddressService;
import com.sandes.speedyDrive.services.CarService;
import com.sandes.speedyDrive.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

	final ClientService  clientService;
	final CarService carService;
	final AddressService addressService;
	public ClientController(ClientService clientService, CarService carService, AddressService addressService) {
		super();
		this.clientService = clientService;
		this.carService = carService;
		this.addressService = addressService;
	}
	

	@PostMapping()
	public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientDto clientDto){
			clientService.checkData(clientDto);
			var clientModel = new ClientModel();
			clientService.transferData(clientDto,clientModel);
			addressService.save(clientModel.getAddress());
			return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientModel> getOneClient(@PathVariable(value= "id") UUID id){
		ClientModel client = clientService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(client);
	}
	
	@GetMapping
	public ResponseEntity<Page<ClientModel>> getAllClients(@PageableDefault(page = 0, size = 2, sort = "id",
	direction = Sort.Direction.ASC) Pageable pageable){
		
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(pageable));
		
	}
	
	@GetMapping("/avaliableClient")
	public ResponseEntity<Page<ClientModel>> getAllAvaliableClients(@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findAllAvaliable(pageable));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable(value= "id") UUID id){
			ClientModel client = clientService.findById(id);
			clientService.changeValues(client.getCars());
			clientService.delete(client);
			return ResponseEntity.status(HttpStatus.OK).body("customer deleted successfully!");	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientModel> updateClient(@RequestBody  ClientDto clientDto, @PathVariable(value= "id") UUID id){
			ClientModel client = clientService.findById(id);
			var clientModel = new ClientModel();
			clientService.transferData(clientDto, clientModel);
			clientModel.setId(client.getId());
			clientModel.setCpf(client.getCpf());
			return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
	}
}
