package com.sandes.speedyDrive.repositores;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sandes.speedyDrive.models.ClientModel;

public interface ClientRepository  extends JpaRepository<ClientModel, UUID>{

	List<ClientModel> findBycarsIsNull();

	boolean existsByCpf(String cpf);

	Page<ClientModel> findBycarsIsNull(Pageable pageable);


}
