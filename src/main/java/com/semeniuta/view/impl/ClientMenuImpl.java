package com.semeniuta.view.impl;

import com.semeniuta.services.ClientService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMenuImpl implements Menu{
    protected final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //get bytes and convert it to string
    protected final ClientService clientService = new ClientServiceImpl();
    protected Menu menu;

    private final static String[] menuItems = {"1. Show all products", "2. Create order", "3. Show all orders", "4. Return to main menu", "0. Exit"};

    @Override
    public void showMenu() throws IOException{
        boolean isRunning = true;

        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":

                    break;
                case "2":
                    break;
                case "3":
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
        System.out.println("bye-bye");
        System.exit(0);
    }
}
