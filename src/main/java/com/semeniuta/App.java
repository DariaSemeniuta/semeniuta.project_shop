package com.semeniuta;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.dao.OrderDao;
import com.semeniuta.dao.ProductDao;
import com.semeniuta.dao.impl.ClientDaoImpl;
import com.semeniuta.dao.impl.OrderDaoImpl;
import com.semeniuta.dao.impl.ProductDaoImpl;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.services.impl.OrderServiceImpl;
import com.semeniuta.services.impl.ProductServiceImpl;
import com.semeniuta.validators.ValidationService;
import com.semeniuta.validators.ValidationServiceImpl;
import com.semeniuta.view.Menu;
import com.semeniuta.view.impl.MainMenuImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ClientDao clientDao = ClientDaoImpl.getInstance();
        ClientService clientService = new ClientServiceImpl(clientDao);

        ProductDao productDao = new ProductDaoImpl();
        ProductService productService = new ProductServiceImpl(productDao);

        OrderDao orderDao = new OrderDaoImpl();
        OrderService orderService = new OrderServiceImpl(orderDao, productDao);


        ValidationService validationService = new ValidationServiceImpl(clientService, productService);
        Menu menu = new MainMenuImpl(br, clientService, productService, orderService, validationService);
        try {
            menu.getUserResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
