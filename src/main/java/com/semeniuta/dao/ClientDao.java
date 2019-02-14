package com.semeniuta.dao;

import com.semeniuta.domain.Client;

import java.util.List;

public interface ClientDao {
    boolean addClient( Client client);
    Client findClient(String name);
    boolean editClient(Client client, String name, String surname, int age, String email, String phone);
    boolean deleteClient(Client client);
    List<Client> returnAllClient();

}
