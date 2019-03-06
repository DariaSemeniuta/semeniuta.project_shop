package com.semeniuta.dao;

public interface DaoDB {
    String DB_URL="jdbc:h2:tcp://localhost/~/Shop";
    String DB_USER = "Test";
    String DB_TABLE_CLIENTS = "Clients";
    String DB_TABLE_PRODUCTS = "Products";
    String DB_TABLE_ORDERS = "Orders";
    String DB_TABLE_ORDER_INFO = "Order_Info";
}
