package com.semeniuta.view.impl;

import com.semeniuta.services.ClientService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMainMenuImpl implements Menu{
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //get bytes and convert it to string
    private final ClientService clientService = new ClientServiceImpl();

    //private final static String menuItems = new String[]{"1. Create client", "2. Update clien info", "3. Delete client"};



    @Override
    public void showMenu() throws IOException{
        boolean isRunning = true;

        while (isRunning) {

            showMenuItems();
            String input = br.readLine();
            switch (input){
                case "1":
                    System.out.println("show 1 menu for admin");
                    break;
                case "2":
                    System.out.println("show 2 menu for client");
                    break;
                case "0":
                    isRunning=false;
                    break;
                default:
                    System.out.println("wrong input");
                    break;
            }

        }
    }

    private void showMenuItems() {
        System.out.println("1. Add client");
        System.out.println("2. Remove client");
        System.out.println("3. Add product");
    }
}
