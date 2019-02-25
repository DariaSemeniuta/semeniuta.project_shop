package com.semeniuta.dao.impl;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDaoImpl implements ClientDao {

    private ClientDaoImpl() {

    }

    private static ClientDao clientDao;

    public static ClientDao getInstance() {
        if (clientDao == null) {
            clientDao = new ClientDaoImpl();
        }
        return clientDao;
    }

    private Map<Long, Client> clients = new HashMap<>();
    private static long generator = 0;

    @Override
    public boolean addClient(Client client) {
        client.setId(generator++);
        clients.put(client.getId(), client);
        return true;
    }

    @Override
    public Client findClient(long id) {
        if (clients.containsKey(id)) {
            return clients.get(id);
        }
        return null;
    }

    @Override
    public boolean editClient(long id, String name, String surname, int age, String email, String phone) {
        Client client = clients.get(id);
        client.setName(name);
        client.setSurname(surname);
        client.setAge(age);
        client.setEmail(email);
        client.setPhone(phone);
        return true;
    }

    @Override
    public boolean deleteClient(long id) {
        clients.remove(id);
        return true;
    }

    @Override
    public List<Client> getAllClient() {
        return new ArrayList<>(clients.values());
    }
}
