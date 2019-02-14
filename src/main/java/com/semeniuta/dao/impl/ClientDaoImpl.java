package com.semeniuta.dao.impl;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.domain.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private List<Client> clients = new ArrayList<>();

    @Override
    public boolean addClient(Client client) {
        clients.add(client);
        return true;
    }

    @Override
    public Client findClient(String name) {
        for (Client client: clients) {
            if(client.getName() == name){
                return client;
            }
        }
        return null;
    }

    @Override
    public boolean editClient(Client client, String name, String surname, int age, String email, String phone) {
        client.setName(name);
        client.setSurname(surname);
        client.setAge(age);
        client.setEmail(email);
        client.setPhone(phone);
        return true;
    }

    @Override
    public boolean deleteClient(Client client) {
        clients.remove(client);
        return true;
    }

    @Override
    public List<Client> returnAllClient() {
        return clients;
    }
}
