package com.semeniuta.dao.impl;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.domain.Client;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClientDaoDBImpl implements ClientDao {
    private static final String DB_TABLE = "Clients";

    public ClientDaoDBImpl() {
        try{
            Class.forName("org.h2.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("System can't connect to DB");
        }
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            Statement statement = connection.createStatement())
        {
            statement.execute("CREATE TABLE IF NOT EXISTS " + DB_TABLE + "(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), SURNAME VARCHAR(255), AGE INT, PHONE VARCHAR(20), EMAIL VARCHAR(100));");
        }catch (SQLException e){
            System.out.println("Can't create table!");
            e.printStackTrace();
        }
    }

    @Override
    public long addClient(Client client) {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO "+DB_TABLE+"VALUES(NAME, SURNAME, AGE,PHONE, EMAIL) VALUES(?, ? ,? , ?, ?);");
            PreparedStatement preparedStatementForId = connection.prepareStatement("SELECT ID FROM "+DB_TABLE+"WHERE PHONE = ?")){
                preparedStatement.setString(1, client.getName());
                preparedStatement.setString(2, client.getSurname());
                preparedStatement.setInt(3, client.getAge());
                preparedStatement.setString(4, client.getPhone());
                preparedStatement.setString(5, client.getEmail());
                preparedStatement.execute();

                preparedStatementForId.setString(1, client.getPhone());
                ResultSet result = preparedStatementForId.executeQuery();

                return result.getLong(1);
                }catch (SQLException e){
            System.out.println("Can't insert contacts!");
            }
        return 0;
    }

    @Override
    public Client findClient(long id) {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + DB_TABLE + "WHERE ID = ?; ")){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            String name = resultSet.getString("NAME");
            String surname = resultSet.getString("SURNAME");
            int age = resultSet.getInt("AGE");
            String phone = resultSet.getString("PHONE");
            String email = resultSet.getString("EMAIL");
            return  new Client(id, name, surname, age, email, phone);
        }catch (SQLException e){
            System.out.println("There is no client");
        }
        return null;
    }

    @Override
    public boolean editClient(long id, String name, String surname, int age, String email, String phone) {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + DB_TABLE + "SET NAME = ?, SURNAME = ?, AGE = ?, PHONE = ?, EMAIL = ? WHERE ID = ?;")){
            preparedStatement.setLong(6, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, email);

            return  preparedStatement.execute();
        }catch (SQLException e){
            System.out.println("Client wasn't updated");
        }
        return false;
    }

    @Override
    public boolean deleteClient(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL,DB_USER,"");
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE * FROM "+DB_TABLE+" WHERE ID = ?;")){
            preparedStatement.setLong(1, id);
             return preparedStatement.execute();
        }catch (SQLException e){
            System.out.println("Client wasn't deleted");
        }
        return false;
    }

    @Override
    public List<Client> getAllClient() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM " + DB_TABLE);
            while (result.next()) {
                Client client = new Client(result.getLong("ID"), result.getString("NAME"), result.getString("SURNAME"), result.getInt("AGE"), result.getString("EMAIL"), result.getString("PHONE"));
                clients.add(client);
            }
        }catch (SQLException e) {
            System.out.println("Error during getting clients");
        }
            return clients;
    }
}
