package com.semeniuta.view.impl;

import com.semeniuta.services.ClientService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminOrderMenuImpl implements Menu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //get bytes and convert it to string
    private final ClientService clientService = new ClientServiceImpl();
    private Menu menu;
    private final static String[] menuItems = {"1.Edit order", "2. Delete client", "3. Return to main menu", "0. Exit"};


    @Override
    public void showMenu() throws IOException{
        boolean isRunning = true;

        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":
                    clientService.createClient("rew", "qwe");
                    break;
                case "2":
                    clientService.updateClient();
                    break;
                case "3":
                    menu = new AdminOrderMenuImpl();
                    menu.showMenu();
                    break;
                case "4":
                    menu = new AdminMainMenuImpl();
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
