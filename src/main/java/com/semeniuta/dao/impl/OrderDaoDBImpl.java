package com.semeniuta.dao.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.domain.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoDBImpl implements OrderDao {
    public OrderDaoDBImpl() {

        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            Statement statement = connection.createStatement())
        {
            statement.execute("CREATE TABLE IF NOT EXISTS " + DB_TABLE_ORDERS + "(ID BIGINT AUTO_INCREMENT PRIMARY KEY, STATUS VARCHAR(50)  NOT NULL, CLIENT_ID BIGINT, FOREIGN KEY(CLIENT_ID) REFERENCES "+DB_TABLE_CLIENTS+"(ID));");
            statement.execute("CREATE TABLE IF NOT EXISTS " + DB_TABLE_ORDER_INFO + "(ID BIGINT AUTO_INCREMENT PRIMARY KEY, ORDER_ID BIGINT, FOREIGN KEY(ORDER_ID) REFERENCES "+DB_TABLE_ORDERS+"(ID) , PRODUCT_ID BIGINT, FOREIGN KEY(PRODUCT_ID) REFERENCES "+DB_TABLE_PRODUCTS+"(ID));");

        }catch (SQLException e){
            System.out.println("Can't create" + DB_TABLE_ORDERS + "table!");
            e.printStackTrace();
        }
    }

    @Override
    public boolean addOrder(Order order) {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO "+DB_TABLE_ORDERS+" (STATUS, CLIENT_ID) VALUES(?, ?);", Statement.RETURN_GENERATED_KEYS);
            Statement statement = connection.createStatement()){
            preparedStatement.setString(1, order.getStatus());
            preparedStatement.setLong(2, order.getIdClient());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                long orderId = resultSet.getInt("ID");
                for (long productId: order.getProducts()) {
                    statement.execute("INSERT INTO "+DB_TABLE_ORDER_INFO+" (ORDER_ID, PRODUCT_ID) VALUES("+orderId+","+productId+")");
                }
            }

            return true;
        }catch (SQLException e){
            System.out.println("Can't add order!");
        }
        return false;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT PRODUCT_ID FROM " + DB_TABLE_ORDER_INFO + " WHERE ORDER_ID = ?; ")){
            ResultSet resultSet = statement.executeQuery(("SELECT * FROM " + DB_TABLE_ORDERS + ";"));

            List<Long> productIds = new ArrayList<>();
            while(resultSet.next()) {
                long id = resultSet.getLong("ID");
                String status = resultSet.getString("STATUS");
                long clientId = resultSet.getLong("CLIENT_ID");
                Order order = new Order(id, status, productIds, clientId);
                preparedStatement.setLong(1, id);

                ResultSet resultSetProducts = preparedStatement.executeQuery();
                while(resultSetProducts.next()){
                    productIds.add(resultSetProducts.getLong("PRODUCT_ID"));
                }
                order.setProducts(productIds);
                orders.add(order);
            }
        }catch (SQLException e) {
            System.out.println("There is no orders");
        }
        return orders ;
    }


    @Override
    public boolean editOrderStatus(long id, String status) {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + DB_TABLE_ORDERS + " SET STATUS = ? WHERE ID = ?;")){
            preparedStatement.setLong(2, id);
            preparedStatement.setString(1, status);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            System.out.println("Order status wasn't updated");
        }
        return false;
    }

    @Override
    public boolean deleteOrder(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL,DB_USER,"");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE * FROM "+DB_TABLE_ORDER_INFO+" WHERE ORDER_ID = ?;");
            PreparedStatement preparedStatementInfo = connection.prepareStatement("DELETE * FROM "+ DB_TABLE_ORDERS +" WHERE ID = ?;")){
            preparedStatementInfo.setLong(1, id);
            preparedStatementInfo.execute();

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            System.out.println("Order wasn't deleted");
        }
        return false;
    }

    @Override
    public Order findOrder(long id) {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + DB_TABLE_ORDERS + " WHERE ID = ?; ");
            PreparedStatement preparedStatementProducts = connection.prepareStatement("SELECT PRODUCT_ID FROM " + DB_TABLE_ORDER_INFO + " WHERE ORDER_ID = ?; ")){
            preparedStatement.setLong(1, id);
            preparedStatementProducts.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = null;
            List<Long> productIds = new ArrayList<>();
            if(resultSet.next()) {
                String status = resultSet.getString("STATUS");
                long clientId = resultSet.getLong("CLIENT_ID");
                order = new Order(productIds, status, clientId);
                resultSet = preparedStatementProducts.executeQuery();
                while(resultSet.next()){
                    productIds.add(resultSet.getLong("PRODUCT_ID"));
                }
                order.setProducts(productIds);
                return order;
            }
        }catch (SQLException e) {
            System.out.println("There is no orders");
        }
        return null;
    }
}
