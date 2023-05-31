package com.sandes.speedyDrive.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sandes.speedyDrive.dtos.ClientDto;
import com.sandes.speedyDrive.models.CarModel;
import com.sandes.speedyDrive.models.ClientModel;
import com.sandes.speedyDrive.repositores.CarRepository;
import com.sandes.speedyDrive.repositores.ClientRepository;
import com.sandes.speedyDrive.services.exceptions.EntityNotCreatedException;
import com.sandes.speedyDrive.services.exceptions.EntityNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ClientService {

	final ClientRepository clientRepository;
	final CarRepository carRepository;

	public ClientService(ClientRepository clientRepository, CarRepository carRepository) {
		super();
		this.clientRepository = clientRepository;
		this.carRepository = carRepository;
	}

	@Transactional
	public ClientModel save(ClientModel clientModel) {
		return clientRepository.save(clientModel);
	}

	public ClientModel findById(UUID id) {
		return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found: "+ id));
	}

	public Page<ClientModel> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clientRepository.findAll(pageable);
	}

	@Transactional
	public void delete(ClientModel clients) {
		// TODO Auto-generated method stub
		clientRepository.delete(clients);
	}

	public List<ClientModel> findAllAvaliable() {
		return clientRepository.findBycarsIsNull();
	}


	public void changeValues(List<CarModel> cars) {
		List<CarModel> entities  = new ArrayList<>();
		
		for(CarModel car : cars) {
			car.setClient(null);
			entities.add(car);
		}
		
		carRepository.saveAll(entities);
		
		
	}

	public boolean existsByCpf(String cpf) {
		return clientRepository.existsByCpf(cpf);
	}

	public Page<ClientModel> findAllAvaliable(Pageable pageable) {
		return clientRepository.findBycarsIsNull(pageable);
	}

	public void checkData(@Valid ClientDto clientDto) {
		
		if(clientRepository.existsByCpf(clientDto.getCpf())) {
			throw new EntityNotCreatedException("cpf inválido!");
		}
		if(clientDto.getAddress() == null) {
			throw new IllegalArgumentException("Endereço não pode ser nulo!");
		}
		
	}

	public void transferData(@Valid ClientDto clientDto, ClientModel clientModel) {
		BeanUtils.copyProperties(clientDto, clientModel);
		clientModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		
	}

}


