package com.semeniuta;

import com.semeniuta.view.impl.MainMenuImpl;

import java.io.IOException;

public class App {
    public static void main (String[] args){
        MainMenuImpl menu = new MainMenuImpl();
        try {
            menu.getUserResponse();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
