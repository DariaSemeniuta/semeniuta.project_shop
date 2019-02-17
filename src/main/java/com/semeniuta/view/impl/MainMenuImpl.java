package com.semeniuta.view.impl;

import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.validators.ValidationService;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenuImpl implements Menu{
    protected BufferedReader br = null;
    protected ClientService clientService = null;
    protected ProductService productService = null;
    protected OrderService orderService = null;
    public ValidationService validationService = null;

    private final static String[] menuItems = {"1. Admin", "2. Client", "0. Exit"};

    public MainMenuImpl(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService, ValidationService validationService) {
        this.br = br;
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
        this.validationService = validationService;
    }

    protected Menu adminMainMenu = new AdminMainMenuImpl(br, clientService, productService, orderService,validationService);
    public Menu clientMenu = new ClientMenuImpl(br, clientService, productService, orderService, validationService);

    @Override
    public void getUserResponse() throws IOException{
        boolean isRunning = true;
        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":
                    adminMainMenu.getUserResponse();
                    break;
                case "2":
                    clientMenu.getUserResponse();
                    break;
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
