package com.semeniuta.view.impl;

import java.io.IOException;


public class AdminClientMenuImpl extends AdminMainMenuImpl {

    private final static String[] menuItems = {"1. Create client", "2. Update client", "3. Delete client", "5. Show all clients", "4. Return to main menu", "0. Exit"};


    @Override
    public void getUserResponse() throws IOException{
        boolean isRunning = true;

        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":
                    createClient();
                    break;
                case "2":
                    updateClient();
                    break;
                case "3":
                    deleteClient();
                    break;
                case "4":
                    showClients();
                    break;
                case "5":
                    menu = new AdminMainMenuImpl();
                    menu.getUserResponse();
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

    private void createClient() throws IOException{
        System.out.print("Please enter name => ");
        String name = br.readLine();

        System.out.print("Please enter second name => ");
        String surname = br.readLine();

        System.out.print("Please enter age => ");
        int age;
        while(true) {
            try {
                age = new Integer(br.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct (Integer)age => ");
            }
        }

        System.out.print("Please enter email => ");
        String email = br.readLine();

        System.out.print("Please enter phone number => ");
        String phone = br.readLine();

        if(clientService.createClient(name, surname, age, email, phone)){
            System.out.println("Client was created");
        }
        else{
            System.out.println("Client was created");
        }
    }

    private void updateClient() throws IOException{
        System.out.print("Please enter name of client that you need to update=> ");
        String name = br.readLine();

        System.out.print("Please enter new name => ");
        String newName = br.readLine();

        System.out.print("Please enter new second name => ");
        String surname = br.readLine();

        System.out.print("Please enter new age => ");
        int age;

        while(true) {
            try {
                age = new Integer(br.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct (Integer)age => ");
            }
        }

        System.out.print("Please enter new email => ");
        String email = br.readLine();

        System.out.print("Please enter new phone number => ");
        String phone = br.readLine();

        if(clientService.updateClient(name, newName, surname, age, email, phone)){
            System.out.println("Client was updated");
        }
        else{
            System.out.println("Client wasn't updated");
        }
    }

    private void deleteClient() throws IOException{
        System.out.println("Please enter name of client that you need to delete");
        String name = br.readLine();

        if(clientService.deleteClient(name)){
            System.out.println("Client was deleted");
        }
        else{
            System.out.println("Client wasn't delete");
        }
    }

    private void showClients(){
        System.out.println("All clients");
        clientService.showClients().forEach(System.out::println);
    }
}
