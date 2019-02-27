package com.semeniuta.services;

import com.semeniuta.domain.Client;

import java.util.List;

public interface ClientService {

    /**
     * Add new client
     *
     * @param name    - String - name of new client
     * @param surname - String - second name of new client
     * @param age     - int - age of new client
     * @param email   - String - email of new client
     * @param phone   - String - phone of the new client
     * @return true if client was successfully added, otherwise - false
     */
    boolean createClient(String name, String surname, int age, String email, String phone);

    /**
     * Update existent client
     *
     * @param id      - long - id of existent client
     * @param name    - String - new name of client
     * @param surname - String - new second name of client
     * @param age     - int - new age of client
     * @param email   - String - new email of client
     * @param phone   - String - new phone of the client
     * @return true if client was successfully changed, otherwise - false
     */
    boolean updateClient(long id, String name, String surname, int age, String email, String phone);

    /**
     * Delete client
     *
     * @param id - Long - id of existent client
     * @return true if client was successfully deleted, otherwise - false
     */
    boolean deleteClient(long id);

    /**
     * Get list of all clients
     *
     * @return list of all clients
     */
    List<Client> showClients();

    /**
     * return Client by id
     *
     * @param id - long - client id
     * @return Client client
     */
    Client showClientInfo(long id);

    /**
     * Check if client with entered phone is already exist
     *
     * @param phone - String - phone number of a client
     * @return true if client is exist, otherwise - false
     */
    boolean isClientExist(String phone);

    /**
     * Check if entered id is exist
     *
     * @param id - long - client id
     * @return true if id is exist, otherwise - false
     */
    boolean isIdExist(long id);

    String getClientEmailByPhone(String phone);
}
