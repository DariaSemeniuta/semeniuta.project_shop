package com.semeniuta.view.impl;

import java.io.IOException;


public class AdminClientMenuImpl extends AdminMainMenuImpl {

    private final static String[] menuItems = {"1. Create client", "2. Update client", "3. Delete client", "5. Show all clients", "4. Return to main menu", "0. Exit"};


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
                    break;
                case "5":
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
