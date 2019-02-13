package com.semeniuta.view.impl;

import com.semeniuta.services.ClientService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminClientMenuImpl extends AdminMainMenuImpl {

    private final static String[] menuItems = {"1. Create client", "2. Update client", "3. Delete client", "4. Return to main menu", "0. Exit"};


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
                    break;
                case "4":
                    menu = new AdminMainMenuImpl();
                    menu.showMenu();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Please enter correct number");
                    break;
            }

        }
        System.out.println("bye-bye");
        System.exit(0);
    }

    private void addClient(){
        System.out.println("Please enter data");
    }

}
