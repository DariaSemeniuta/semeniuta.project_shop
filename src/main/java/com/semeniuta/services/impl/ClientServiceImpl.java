package com.semeniuta.services.impl;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.dao.impl.ClientDaoImpl;
import com.semeniuta.domain.Client;
import com.semeniuta.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();
    @Override
    public boolean createClient(String name, String surname, int age, String email, String phone) {
        Client client = new Client(name, surname, age, email, phone);
        return clientDao.addClient(client);
    }

    @Override
    public boolean updateClient(String oldName, String name, String surname, int age, String email, String phone) {
        Client client = clientDao.findClient(oldName);
        return clientDao.editClient(client, name, surname, age, email, phone);
    }

    @Override
    public boolean deleteClient(String name) {
        Client client = clientDao.findClient(name);
        return clientDao.deleteClient(client);
    }

    @Override
    public List<Client> showClients() {
        return clientDao.returnAllClient();
    }


}
