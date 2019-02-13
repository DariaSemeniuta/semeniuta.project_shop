package com.semeniuta.services;

public interface ClientService {
    /**
     * add documentation
     */
    boolean createClient(String name, String phone);
    boolean updateClient(String name);
    boolean deleteClient(String name);
    void showClients();
}
