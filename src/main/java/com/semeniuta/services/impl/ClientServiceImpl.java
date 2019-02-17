package com.semeniuta.services.impl;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.dao.impl.ClientDaoImpl;
import com.semeniuta.domain.Client;
import com.semeniuta.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao){
        this.clientDao = clientDao;
    }
    @Override
    public boolean createClient(String name, String surname, int age, String email, String phone) {
        Client client = new Client(name, surname, age, email, phone);
        return clientDao.addClient(client);
    }

    @Override
    public boolean updateClient(long id, String name, String surname, int age, String email, String phone) {
        Client client = clientDao.findClient(id);
        return clientDao.editClient(client, name, surname, age, email, phone);
    }

    @Override
    public boolean deleteClient(long id) {
        Client client = clientDao.findClient(id);
        return clientDao.deleteClient(client);
    }

    @Override
    public List<Client> showClients() {
        return clientDao.returnAllClient();
    }

    @Override
    public Client showClientInfo(long id) {
        return clientDao.findClient(id);
    }

    @Override
    public boolean isClientExist(String phone) {
        List<Client> clients = clientDao.returnAllClient();
        for (Client client: clients ) {
            if(client.getPhone().equals(phone)){
                return true;
            }
        }
        return false;
    }
}
