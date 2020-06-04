package org.polytech.course.service;

import org.polytech.course.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> listClients();

    Client findClient(long id);
    Client addClient(Client client);
    Client updateClient(long id, Client client);
    void deleteClient(long id);
}
