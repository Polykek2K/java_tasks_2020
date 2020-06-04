package org.polytech.course.web;

import org.polytech.course.entity.Client;
import org.polytech.course.exception.ClientNotFoundException;
import org.polytech.course.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("clients/all")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> list = clientService.listClients();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(clientService.findClient(id), HttpStatus.OK);
        } catch (ClientNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!");
        }
    }

    @PostMapping(value = "clients/add", consumes = "application/json", produces = "application/json")
    public Client addClient(@RequestBody Client newClient) {
        return clientService.addClient(newClient);
    }

    @PutMapping(value = "clients/{id}", consumes = "application/json", produces = "application/json")
    public Client updateClient(@PathVariable("id") long id, @RequestBody Client client) {
        try {
            return clientService.updateClient(id, client);
        } catch (ClientNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!");
        }
    }

    @DeleteMapping("clients/{id}")
    public void deleteClient(@PathVariable("id") long id) {
        try {
            clientService.deleteClient(id);
        } catch (ClientNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!");
        }
    }
}