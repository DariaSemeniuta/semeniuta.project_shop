package com.semeniuta.services.impl;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.dao.impl.ClientDaoImpl;
import com.semeniuta.domain.Client;
import com.semeniuta.services.ClientService;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();
    @Override
    public boolean createClient(String name, String phone) {
        Client client = new Client(name, phone);
        boolean res = clientDao.saveClient();
        return res;
    }

    @Override
    public boolean updateClient(String name) {
        return false;
    }

    @Override
    public boolean deleteClient(String name) {
        return false;
    }

    @Override
    public void showClients() {

    }
}
