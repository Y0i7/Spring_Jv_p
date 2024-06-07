package com.ORY.Tienda.Service;

import com.ORY.Tienda.Domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();
    Optional<Client> findById(Integer id);
    Client saveClient(Client client);
    void deleteClient(Integer id);

}
