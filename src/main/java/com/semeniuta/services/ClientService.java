package com.semeniuta.services;

import com.semeniuta.domain.Client;

import java.util.List;

public interface ClientService {
    /**
     * add documentation
     */
    boolean createClient(String name, String surname, int age, String email, String phone);
    boolean updateClient(String oldName, String name, String surname, int age, String email, String phone);
    boolean deleteClient(String name);
    List<Client> showClients();
}
