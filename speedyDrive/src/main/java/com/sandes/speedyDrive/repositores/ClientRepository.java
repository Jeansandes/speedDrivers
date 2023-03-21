package com.sandes.speedyDrive.repositores;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandes.speedyDrive.models.ClientModel;

public interface ClientRepository  extends JpaRepository<ClientModel, UUID>{

}
