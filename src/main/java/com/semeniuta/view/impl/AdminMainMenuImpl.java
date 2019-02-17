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

public class AdminMainMenuImpl extends  MainMenuImpl implements Menu{

    private final static String[] menuItems = {"1. Admin client side", "2. Admin product side", "3. Admin order side", "4. Return to main menu", "0. Exit"};
    private Menu adminClientMenu = new AdminClientMenuImpl(br, clientService, productService, orderService, validationService);
    private Menu adminProductMenu = new AdminProductMenuImpl(br, productService);
    private Menu adminOrderMenu = new AdminOrderMenuImpl(br, orderService, validationService);
    Menu clientMenu = super.clientMenu;

    public AdminMainMenuImpl(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService, ValidationService validationService) {
        super(br, clientService, productService, orderService, validationService);
    }


    @Override
    public void getUserResponse() throws IOException{
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
                    super.getUserResponse();
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
