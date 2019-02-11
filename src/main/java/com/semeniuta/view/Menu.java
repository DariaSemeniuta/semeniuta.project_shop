package com.semeniuta.view;

import java.io.IOException;

public interface Menu {
    void showMenu() throws IOException;
    default void showMenuItems(String[] menuItems){
        System.out.println("_________________________________");
        for (String item: menuItems) {
            System.out.println("|  "+item+"  |");
        }
        System.out.println("_________________________________");
    }
}
