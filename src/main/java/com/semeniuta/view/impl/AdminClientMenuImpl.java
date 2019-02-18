package com.semeniuta.view.impl;

import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.validators.ValidationService;

import java.io.BufferedReader;
import java.io.IOException;

public class AdminClientMenuImpl extends ClientMenuImpl {

    private final static String[] menuItems = {"1. Create client", "2. Update client", "3. Delete client", "4. Show all clients", "5. Show info about client", "6. Return to main menu", "0. Exit"};

    public AdminClientMenuImpl(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService, ValidationService validationService) {
        super(br, clientService, productService, orderService, validationService);
    }

    @Override
    public void getUserResponse() throws IOException {
        boolean isRunning = true;

        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input) {
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
                    showClientInfo();
                    break;
                case "6":
                    return;
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

    private void deleteClient() throws IOException {
        System.out.println("Delete client:");
        long id = readId();
        try{
            validationService.validateClientId(id);
            if (clientService.deleteClient(id)) {
                System.out.println("Client was deleted");
            } else {
                System.out.println("Client wasn't delete");
            }
        }catch (BusinessExceptions e){
            e.printStackTrace();
        }

    }

    private void showClients() {
        System.out.println("All clients");
        clientService.showClients().forEach(System.out::println);
    }
}
