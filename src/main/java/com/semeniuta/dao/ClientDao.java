package com.semeniuta.dao;

import com.semeniuta.domain.Client;

import java.util.List;

public interface ClientDao {

    /**
     * Add new client to dao
     *
     * @param client - Client - object that should to be added
     * @return true if client was successfully added, otherwise - false
     */
    long addClient(Client client);

    /**
     * Find client by name // other criterias for search will be implemented later
     *
     * @param id - String - name of client
     * @return Client object
     */
    Client findClient(long id);

    /**
     * Change info about client
     *
     * @param id      - long - object id that should be changed
     * @param name    - String - new name of client
     * @param surname - String - new second name of client
     * @param age     - int - new age of client
     * @param email   - String - new email of client
     * @param phone   - String - new phone of client
     * @return true if client was successfully changed, otherwise - false
     */
    boolean editClient(long id, String name, String surname, int age, String email, String phone);

    /**
     * Delete client from DAO
     *
     * @param id - lond - object id that should be removed
     * @return true if client was successfully removed, otherwise - false
     */
    boolean deleteClient(long id);

    /**
     * Return list of all clients in DAO
     *
     * @return list of all clients in DAO
     */
    List<Client> getAllClient();

}
