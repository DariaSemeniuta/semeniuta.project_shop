package com.semeniuta.dao.impl;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.domain.Client;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientDaoDBImpl implements ClientDao {
    public ClientDaoDBImpl() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("System can't connect to DB");
        }
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS " + DB_TABLE_CLIENTS + "(ID BIGINT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255) NOT NULL, SURNAME VARCHAR(255) NOT NULL, AGE INT, PHONE VARCHAR(20) NOT NULL, EMAIL VARCHAR(100) NOT NULL);");
        } catch (SQLException e) {
            System.out.println("Can't create table!");
            e.printStackTrace();
        }
    }

    @Override
    public long addClient(Client client) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + DB_TABLE_CLIENTS + "(NAME, SURNAME, AGE,PHONE, EMAIL) VALUES(?, ? ,? , ?, ?);");
             PreparedStatement preparedStatementForId = connection.prepareStatement("SELECT ID FROM " + DB_TABLE_CLIENTS + " WHERE PHONE = ?")) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setInt(3, client.getAge());
            preparedStatement.setString(4, client.getPhone());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.execute();

            preparedStatementForId.setString(1, client.getPhone());
            ResultSet result = preparedStatementForId.executeQuery();
            if (result.next()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Can't insert client!");
        }
        return -1;
    }

    @Override
    public Client findClient(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + DB_TABLE_CLIENTS + " WHERE ID = ?; ")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String surname = resultSet.getString("SURNAME");
                int age = resultSet.getInt("AGE");
                String phone = resultSet.getString("PHONE");
                String email = resultSet.getString("EMAIL");
                return new Client(id, name, surname, age, email, phone);
            }
        } catch (SQLException e) {
            System.out.println("There is no client");
        }
        return null;
    }

    @Override
    public boolean editClient(long id, String name, String surname, int age, String email, String phone) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + DB_TABLE_CLIENTS + " SET NAME = ?, SURNAME = ?, AGE = ?, PHONE = ?, EMAIL = ? WHERE ID = ?;")) {
            preparedStatement.setLong(6, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, email);

            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Client wasn't updated");
        }
        return false;
    }

    @Override
    public boolean deleteClient(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + DB_TABLE_CLIENTS + " WHERE ID = ?;");
             PreparedStatement preparedStatementOrders = connection.prepareStatement("DELETE FROM " + DB_TABLE_ORDERS + " WHERE CLIENT_ID = ?;")) {
            preparedStatementOrders.setLong(1, id);
            preparedStatementOrders.execute();
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Client wasn't deleted");
        }
        return false;
    }

    @Override
    public List<Client> getAllClient() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM " + DB_TABLE_CLIENTS);
            while (result.next()) {
                Client client = new Client(result.getLong("ID"), result.getString("NAME"), result.getString("SURNAME"), result.getInt("AGE"), result.getString("EMAIL"), result.getString("PHONE"));
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("Error during getting clients");
        }
        return clients;
    }
}
