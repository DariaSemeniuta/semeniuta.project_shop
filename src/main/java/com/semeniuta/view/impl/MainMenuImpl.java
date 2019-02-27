package com.semeniuta.view.impl;

import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.validators.ValidationService;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenuImpl implements Menu {
    protected BufferedReader br = null;
    protected ClientServiceImpl clientService = null;
    protected ProductService productService = null;
    protected OrderService orderService = null;
    public ValidationService validationService = null;

    private final static String[] menuItems = {"1. Admin", "2. Client", "0. Exit"};


    private AdminMainMenuImpl adminMainMenu;
    private ClientMenuImpl clientMenu;
    private AdminOrderMenuImpl orderMenu;
    private Menu adminClientMenu;
    private AdminProductMenuImpl adminProductMenu;

    public MainMenuImpl(BufferedReader br, ClientServiceImpl clientService, ProductService productService, OrderService orderService, ValidationService validationService) {
        this.br = br;
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
        this.validationService = validationService;
        this.orderMenu = new AdminOrderMenuImpl(br, orderService, validationService);
        this.adminProductMenu = new AdminProductMenuImpl(br, productService, validationService);

        this.adminClientMenu = new AdminClientMenuImpl(br, clientService, validationService, orderMenu, adminProductMenu);
        this.adminMainMenu = new AdminMainMenuImpl(br, clientService, productService, orderService, validationService, adminClientMenu, adminProductMenu, orderMenu);
        this.clientMenu = new ClientMenuImpl(br, clientService, validationService, orderMenu, adminProductMenu);

    }


    @Override
    public void getUserResponse() throws IOException {


        boolean isRunning = true;
        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input) {
                case "1":
                    adminMainMenu.logIn();
                    break;
                case "2":
                    clientMenu.getUser();
                    break;
                case "0":
                    isRunning = false;
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
