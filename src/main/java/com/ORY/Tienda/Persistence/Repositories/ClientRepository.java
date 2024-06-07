package com.ORY.Tienda.Persistence.Repositories;

import com.ORY.Tienda.Persistence.Entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer>  {

}
