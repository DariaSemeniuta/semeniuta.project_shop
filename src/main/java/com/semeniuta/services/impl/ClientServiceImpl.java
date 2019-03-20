package com.semeniuta.services.impl;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.domain.Client;
import com.semeniuta.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    private long clientId = -1l;

    public long getClientId() {
        return clientId;
    }

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public boolean createClient(String name, String surname, int age, String email, String phone) {
        Client client = new Client(name, surname, age, email, phone);
        if((clientId=clientDao.addClient(client)) == -1){
            return false;
        }

        return true;
    }

    @Override
    public boolean updateClient(long id, String name, String surname, int age, String email, String phone) {
        return clientDao.editClient(id, name, surname, age, email, phone);
    }

    @Override
    public boolean deleteClient(long id) {
        return clientDao.deleteClient(id);
    }

    @Override
    public List<Client> showClients() {
        return clientDao.getAllClient();
    }

    @Override
    public Client showClientInfo(long id) {
        return clientDao.findClient(id);
    }

    @Override
    public boolean isClientExist(String phone) {
        List<Client> clients = clientDao.getAllClient();
        for (Client client : clients) {
            if (client.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isIdExist(long id) {
        if (clientDao.findClient(id) == null) {
            return false;
        }
        return true;
    }

    //use for client log in
    @Override
    public String getClientEmailByPhone(String phone) {
        List<Client> clients = clientDao.getAllClient();
        if(clients != null) {
            for (Client client : clients) {
                if (client.getPhone().equals(phone)) {
                    clientId = client.getId();
                    return client.getEmail();
                }
            }
        }
        return null;
    }

    @Override
    public Client getClientByPhone(String phone) {
        List<Client> clients = clientDao.getAllClient();
        if(clients != null) {
            for (Client client : clients) {
                if (client.getPhone().equals(phone)) {
                    return client;
                }
            }
        }
        return null;
    }
}
