package com.semeniuta.dao.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.domain.Order;

import java.sql.*;
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
        return null;
    }


    @Override
    public boolean editOrderStatus(long id, String status) {
        return false;
    }

    @Override
    public boolean deleteOrder(long id) {
        return false;
    }

    @Override
    public Order findOrder(long id) {
        return null;
    }
}
