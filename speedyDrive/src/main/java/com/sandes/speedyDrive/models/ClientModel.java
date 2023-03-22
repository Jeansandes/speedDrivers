package com.sandes.speedyDrive.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ClientModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String cpf;
	
	private LocalDateTime registrationDate;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	@OneToMany(mappedBy = "client")
	private List<CarModel> cars = new ArrayList<>();
	
	public ClientModel() {
		
	}

	
	public ClientModel(UUID id, String name, String cpf, String address) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.registrationDate = LocalDateTime.now(ZoneId.of("UTC"));
		this.address = address;
	}


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CarModel> getCars() {
		return cars;
	}

	public void setCars(List<CarModel> cars) {
		this.cars = cars;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	

}
