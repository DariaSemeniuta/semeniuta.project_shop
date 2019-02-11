package com.semeniuta.view.impl;

import com.semeniuta.services.ClientService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminClientMenuImpl implements Menu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //get bytes and convert it to string
    private final ClientService clientService = new ClientServiceImpl();
    private Menu menu;
    private final static String[] menuItems = {"1. Admin client side", "2. Admin product side", "3. Admin order side", "4. Return to main menu", "0. Exit"};


    @Override
    public void showMenu() throws IOException{
        boolean isRunning = true;

        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":
                    menu = new AdminClientMenuImpl();
                    menu.showMenu();
                    break;
                case "2":
                    menu = new AdminProductMenuImpl();
                    menu.showMenu();
                    break;
                case "3":
                    menu = new AdminOrderMenuImpl();
                    menu.showMenu();
                    break;
                case "4":
                    menu = new MainMenuImpl();
                    menu.showMenu();
                    break;
                case "0":
                    isRunning=false;
                    break;
                default:
                    System.out.println("Please enter correct number");
                    break;
            }

        }
    }

}
