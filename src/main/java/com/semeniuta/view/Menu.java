package com.semeniuta.view;

import java.io.IOException;

public interface Menu {

    /**
     * Menu.getUserResponse() method gets responce from user and handles
     * @throws IOException
     */
    void getUserResponse() throws IOException;

    /**
     * Display menu items
     * @param menuItems - String[] - array of menu items that should be displayed(e.g. "1. Admin menu")
     */
    default void showMenuItems(String[] menuItems){
        System.out.println("_________________________________");
        for (String item: menuItems) {
            System.out.println("|  "+item+"  |");
        }
        System.out.println("_________________________________");
    }
}
