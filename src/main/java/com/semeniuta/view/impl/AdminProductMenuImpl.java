package com.semeniuta.view.impl;

import java.io.IOException;

public class AdminProductMenuImpl extends AdminMainMenuImpl {

    private final static String[] menuItems = {"1. Create product", "2. Edit product", "3. Delete product", "4. Show all products", "5. Return to main menu", "0. Exit"};

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
