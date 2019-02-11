package com.semeniuta.services.impl;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.dao.impl.ClientDaoImpl;
import com.semeniuta.domain.Client;
import com.semeniuta.services.ClientService;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();
    @Override
    public void createClient(String name, String phone) {
        Client client = new Client(name, phone);
        boolean res = clientDao.saveClient();
    }

    @Override
    public void updateClient() {

    }

    @Override
    public void deleteClient() {

    }
}
