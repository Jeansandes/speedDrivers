package com.sandes.speedyDrive.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sandes.speedyDrive.models.CarModel;
import com.sandes.speedyDrive.models.ClientModel;
import com.sandes.speedyDrive.repositores.ClientRepository;

@Service
public class ClientService {

	final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	public ClientModel save(ClientModel clientModel) {
		return clientRepository.save(clientModel);
	}

	public Optional<ClientModel> findById(UUID id) {
		return clientRepository.findById(id);
	}

	public Page<ClientModel> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clientRepository.findAll(pageable);
	}

	public void delete(ClientModel clients) {
		// TODO Auto-generated method stub
		clientRepository.delete(clients);
	}

	public List<ClientModel> findAllAvaliable() {
		return clientRepository.findBycarsIsNull();
	}
}
