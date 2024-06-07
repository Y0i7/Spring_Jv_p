package com.ORY.Tienda.Service;

import com.ORY.Tienda.Domain.Client;
import com.ORY.Tienda.Persistence.Entities.ClientEntity;
import com.ORY.Tienda.Persistence.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import java.util.Optional;

@Service("BD2")
@ConditionalOnProperty(
        value="clients.strategy",
        havingValue = "EN_BD2")

public class ClientServiceBDImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        List<ClientEntity> entities = clientRepository.findAll();
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findById(Integer id){
        Optional<ClientEntity> entity = clientRepository.findById(id);
        return entity.map(this::mapToDto);
    }

    @Override
    public Client saveClient(Client client) {
        //Mapeo de Client a ClientEntity
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setUser_name(client.getUser_name());
        clientEntity.setPassword(client.getPassword());
        clientEntity.setEmail(client.getEmail());
        ClientEntity savedEntity = clientRepository.save(clientEntity);
        return mapToDto(savedEntity);
    }

    @Override
    public void deleteClient(Integer id){
        clientRepository.deleteById(id);
    }

    private Client mapToDto(ClientEntity entity) {
        Client dto = new Client();
        dto.setId(entity.getId());
        dto.setUser_name(entity.getUser_name());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    private ClientEntity mapToEntity(Client dto) {
        ClientEntity entity = new ClientEntity();
        entity.setId(dto.getId());
        entity.setUser_name(dto.getUser_name());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        return entity;
    }

}
