package com.ORY.Tienda.Controller;

import com.ORY.Tienda.Domain.Client;
import com.ORY.Tienda.Service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/Clients")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getCLientById(@PathVariable Integer id){
        Optional<Client> client = clientService.findById(id);
        if(client.isPresent()){
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.saveClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> editClient(@PathVariable Integer id, @RequestBody Client client){
        Optional<Client> existingClient = clientService.findById(id);
        if(existingClient.isPresent()){
            client.setId(id);
            Client updateClient = clientService.saveClient(client);
            return new ResponseEntity<>(updateClient, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Integer id){
        Optional<Client> existingClient = clientService.findById(id);
        if(existingClient.isPresent()){
            clientService.deleteClient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    //@Autowired
    //private ClientServiceIMPL clientServiceIMPL;

    /*@GetMapping()
    public ResponseEntity<?>getClients(){
        return ResponseEntity.ok(clientServiceIMPL.getClients());
    }

    @GetMapping("{id}")
    public ResponseEntity<?>getClient(@PathVariable int id){
        try{
            Client foundClient = clientServiceIMPL.getClient(id);
            return ResponseEntity.ok(foundClient);
        }catch (Exception e){
            log.info("Sorry the requested Client Hasn't been found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?>postClient(@RequestBody Client client){
        if(clientServiceIMPL.addClient(client)){
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(client.getId())
                    .toUri();
            return ResponseEntity.created(location).body(client);
        }
        log.error("Create new client hasn't success");
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<?> putClient(@RequestBody Client client){
        Client changedclient = clientServiceIMPL.updateClient(client);
        if(changedclient != null){
            return ResponseEntity.ok(changedclient);
        }
        log.warn("CLIENT NOT FOUND");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClient(@PathVariable int id){
        Optional client = clientServiceIMPL.deleteClient(id);
        if(client.isPresent()){
            return ResponseEntity.ok(client.get());
        }
        log.error("CLIENT NOT FOUND");
        return ResponseEntity.notFound().build();
    }

     */

}
