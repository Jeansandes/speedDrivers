package com.sandes.speedyDrive.services;

import org.springframework.stereotype.Service;

import com.sandes.speedyDrive.models.Address;
import com.sandes.speedyDrive.repositores.AddressRepository;

import jakarta.transaction.Transactional;


@Service
public class AddressService {

	final AddressRepository addressRepository;
	
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	@Transactional
	public Address save(Address address) {
		return addressRepository.save(address);
	}





	
}
