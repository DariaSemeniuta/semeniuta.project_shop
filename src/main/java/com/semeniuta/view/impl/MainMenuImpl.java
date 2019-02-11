package com.semeniuta.view.impl;

import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenuImpl implements Menu{
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //get bytes and convert it to string
    private final AdminMainMenuImpl adminMainMenuImpl = new AdminMainMenuImpl();
    private final ClientMenuImpl clientMenuImpl = new ClientMenuImpl();

    private final static String[] menuItems = {"1. Admin", "2. Client", "0. Exit"};
    private final Menu mainMenu = new MainMenuImpl();
    private final Menu adminClientMenu = new AdminClientMenuImpl();
    /*@Override
    public void showMenuItems(String[] menuItems) {
        System.out.println("_________________________________");
        for (String item: menuItems) {
            System.out.println("|  "+item+"  |");
        }
        System.out.println("_________________________________");

    }*/
    @Override
    public void showMenu() throws IOException{
        boolean isRunning = true;
        while (isRunning) {
            mainMenu.showMenuItems(menuItems);
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
        System.out.println("bye");
        System.exit(0);
    }


}
