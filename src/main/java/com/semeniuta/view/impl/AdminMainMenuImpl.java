package com.semeniuta.view.impl;

import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.validators.ValidationService;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMainMenuImpl implements Menu{

    private final BufferedReader br;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;
    private final ValidationService validationService;

    public AdminMainMenuImpl(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService, ValidationService validationService) {
        this.br = br;
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
        this.validationService = validationService;
    }

    private final static String[] menuItems = {"1. Admin client side", "2. Admin product side", "3. Admin order side", "4. Return to main menu", "0. Exit"};


    @Override
    public void getUserResponse() throws IOException{
        Menu adminClientMenu = new AdminClientMenuImpl(br, clientService, productService, orderService, validationService);
        Menu adminProductMenu = new AdminProductMenuImpl(br, productService);
        Menu adminOrderMenu = new AdminOrderMenuImpl(br, orderService, validationService);

        boolean isRunning = true;

        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":
                    adminClientMenu.getUserResponse();
                    break;
                case "2":
                    adminProductMenu.getUserResponse();
                    break;
                case "3":
                    adminOrderMenu.getUserResponse();
                    break;
                case "4":
                    return;
                case "0":
                    isRunning=false;
                    break;
                default:
                    System.out.println("Please enter correct number");
                    break;
            }

        }
        System.out.println("bye-bye");
        System.exit(0);
    }
}
