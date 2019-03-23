package com.semeniuta.dao.impl;

import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDaoDBImpl implements ProductDao {
    public ProductDaoDBImpl() {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS " + DB_TABLE_PRODUCTS + "(ID BIGINT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255)  NOT NULL, PRICE NUMERIC(10)  NOT NULL);");
        } catch (SQLException e) {
            System.out.println("Can't create" + DB_TABLE_PRODUCTS + "table!");
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM " + DB_TABLE_PRODUCTS);
            while (result.next()) {
                Product product = new Product(result.getLong("ID"), result.getString("NAME"), result.getBigDecimal("PRICE"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error during getting products");
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + DB_TABLE_PRODUCTS + "(NAME, PRICE) VALUES(?, ?);")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Can't add product!");
        }
        return false;
    }

    @Override
    public boolean editProduct(long id, String name, BigDecimal price) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + DB_TABLE_PRODUCTS + " SET NAME = ?, PRICE = ? WHERE ID = ?;")) {
            preparedStatement.setLong(3, id);
            preparedStatement.setString(1, name);
            preparedStatement.setBigDecimal(2, price);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Product wasn't updated");
        }
        return false;
    }

    @Override
    public boolean deleteProduct(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + DB_TABLE_PRODUCTS + " WHERE ID = ?;");
             PreparedStatement preparedStatementOrdersInfo = connection.prepareStatement("DELETE FROM " + DB_TABLE_ORDER_INFO + " WHERE PRODUCT_ID = ?;")) {
            preparedStatementOrdersInfo.setLong(1, id);
            preparedStatementOrdersInfo.execute();
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Product wasn't deleted");
        }
        return false;
    }

    @Override
    public Product findProduct(long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, "");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + DB_TABLE_PRODUCTS + " WHERE ID = ?; ")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("NAME");
                BigDecimal price = resultSet.getBigDecimal("PRICE");
                return new Product(id, name, price);
            }
        } catch (SQLException e) {
            System.out.println("There is no product");
        }
        return null;
    }
}
