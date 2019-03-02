package com.semeniuta.view.impl;

import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.validators.ValidationService;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;

public class AdminMainMenuImpl implements Menu {

    private final BufferedReader br;

    private final ValidationService validationService;

    private Menu adminClientMenu;
    private Menu adminProductMenu;
    private Menu adminOrderMenu;

    public AdminMainMenuImpl(BufferedReader br, ValidationService validationService, Menu adminClientMenu, Menu adminProductMenu, Menu adminOrderMenu) {
        this.br = br;
        this.validationService = validationService;
        this.adminClientMenu = adminClientMenu;
        this.adminProductMenu = adminProductMenu;
        this.adminOrderMenu = adminOrderMenu;
    }
    private final String adminLogName = "admin";
    private final String adminPwd = "admin";

    private final static String[] menuItems = {"1. Admin client side", "2. Admin product side", "3. Admin order side", "4. Return to main menu", "0. Exit"};

    public void logIn() throws IOException{
        System.out.println("Please enter login or 0 for exit =>");
        String input;
        while (!adminLogName.equals(input=br.readLine())){
            if (input.equals("0")){
                return;
            }
            System.out.println("Please enter correct login");
        }
        System.out.println("Please enter password =>");
        while (!adminPwd.equals(br.readLine())){
            System.out.println("Please enter correct password!");
        }
        getUserResponse();
    }

    @Override
    public void getUserResponse() throws IOException {

        boolean isRunning = true;

        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input) {
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
