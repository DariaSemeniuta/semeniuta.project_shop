package com.semeniuta.services;

import com.semeniuta.domain.Client;

import java.util.List;

public interface ClientService {
    /**
     * add documentation
     */
    boolean createClient(String name, String surname, int age, String email, String phone);
    boolean updateClient(Client client, String name, String surname, int age, String email, String phone);
    boolean deleteClient(Client client);
    Client findClient(String name);
    List<Client> showClients();
}
