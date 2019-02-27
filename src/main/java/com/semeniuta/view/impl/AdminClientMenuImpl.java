package com.semeniuta.view.impl;

import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.validators.ValidationService;

import java.io.BufferedReader;
import java.io.IOException;

public class AdminClientMenuImpl extends ClientMenuImpl {

    private final static String[] menuItems = {"1. Create client", "2. Update client", "3. Delete client", "4. Show all clients", "5. Show info about client", "6. Return to main menu", "0. Exit"};

    public AdminClientMenuImpl(BufferedReader br, ClientServiceImpl clientService, ValidationService validationService, AdminOrderMenuImpl orderMenu, AdminProductMenuImpl adminProductMenu) {
        super(br, clientService, validationService, orderMenu, adminProductMenu);
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
        try {
            validationService.validateClientId(id);
            if (clientService.deleteClient(id)) {
                System.out.println("Client was deleted");
            } else {
                System.out.println("Client wasn't delete");
            }
        } catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }

    }

    private void showClients() {
        System.out.println("All clients");
        clientService.showClients().forEach(System.out::println);
    }

    protected void updateClient() throws IOException {
        long id = readId();
        try {
            validationService.validateClientId(id);
            System.out.print("Please enter new name => ");
            String newName = br.readLine();

            System.out.print("Please enter new second name => ");
            String surname = br.readLine();

            int age = inputAge();
            String email = inputEmail();
            String phone = inputPhone();

            if (clientService.updateClient(id, newName, surname, age, email, phone)) {
                System.out.println("Client was updated");
            } else {
                System.out.println("Client wasn't updated");
            }
        } catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    protected void showClientInfo() throws IOException {
        System.out.println("Info about client:");
        long id = readId();
        try {
            validationService.validateClientId(id);
            System.out.println(clientService.showClientInfo(id).toString());
        } catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}
