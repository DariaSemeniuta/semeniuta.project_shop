package com.semeniuta.view.impl;

import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenuImpl implements Menu{
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //get bytes and convert it to string

    private final static String[] menuItems = {"1. Admin", "2. Client", "0. Exit"};
    private Menu menu;

    @Override
    public void showMenu() throws IOException{
        boolean isRunning = true;
        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":
                    menu = new AdminMainMenuImpl();
                    menu.showMenu();
                    break;
                case "2":
                    menu = new ClientMenuImpl();
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
