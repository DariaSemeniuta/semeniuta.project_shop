package com.semeniuta.view.impl;

import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.validators.ValidationService;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientMenuImpl implements Menu {

    private final static String[] menuItems = {"1. Create new user", "2. Edit your profile", "3. Show user profile", "4. Show all products", "5. Create order", "6. Show all orders", "7. Return to main menu", "8. Log out", "0. Exit"};


    protected final BufferedReader br;
    protected final ClientService clientService;

    protected final ValidationService validationService;

    private Menu orderMenu;
    private Menu productMenu;

    public ClientMenuImpl(BufferedReader br, ClientService clientService, ValidationService validationService, Menu orderMenu, Menu productMenu) {
        this.br = br;
        this.clientService = clientService;
        this.validationService = validationService;
        this.orderMenu = orderMenu;
        this.productMenu = productMenu;
    }

    public void getUser() throws IOException {
        String[] items = {"1. Log in", "2. Registration", "3. Return to main menu", "0. Exit"};
        this.showMenuItems(items);
        boolean isRunning = true;
        while (isRunning) {
            String input = br.readLine();
            switch (input) {
                case "1":
                    logInClient();
                    break;
                case "2":
                    createClient();
                    getUserResponse();
                    break;
                case "3":
                    return;
                case "0":
                    System.exit(0);

                default:
                    return;
            }
        }
    }


    private void logInClient() throws IOException{
        String email = inputEmail();
        String phone =  inputPhone();
        if(clientService.isClientExist(phone)) {
            if (clientService.getClientEmailByPhone(phone).equals(email)) {
                System.out.println("Successfully logged in :)");
                getUserResponse();
            }
        }
        System.out.println("There is no client! Incorrect email or phone.");
        getUser();
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
                    showClientInfo();
                    break;
                case "4":
                    ((AdminProductMenuImpl) productMenu).showProducts();
                    break;
                case "5":
                    ((AdminOrderMenuImpl)orderMenu).createOrder(((ClientServiceImpl)clientService).getClientId());
                    break;
                case "6":
                    ((AdminOrderMenuImpl)orderMenu).showOrders(((ClientServiceImpl)clientService).getClientId());
                    break;
                case "7":
                    getUser();
                    break;
                case "8":
                    getUser();
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Please enter correct number");
                    break;
            }

        }
        System.out.println("bye-bye");
        System.exit(0);
    }


    public int inputAge() throws IOException {
        boolean flag = true;
        int age = 0;

        while (flag) {
            System.out.print("Please enter age => ");
            String input;
            while (!(validationService.readInt(input = br.readLine()))) {
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct (Integer)age => ");
            }
            age = Integer.parseInt(input);
            try {
                validationService.validateAge(age);
                flag = false;
            } catch (BusinessExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        return age;
    }

    public String inputEmail() throws IOException {
        boolean flag = true;
        String email = "";

        while (flag) {
            System.out.print("Please enter email => ");
            try {
                validationService.readEmail(email = br.readLine());
                flag = false;
            } catch (BusinessExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        return email;
    }


    public String inputPhone() throws IOException {
        boolean flag = true;
        String phone = "";

        while (flag) {
            System.out.print("Please enter phone number => ");
            try {
                validationService.readPhone(phone = br.readLine());
                flag = false;
            } catch (BusinessExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        return phone;
    }

    protected void createClient() throws IOException {
        System.out.print("Please enter name => ");
        String name = br.readLine();

        System.out.print("Please enter second name => ");
        String surname = br.readLine();
        int age = inputAge();

        String email = inputEmail();
        String phone = inputPhone();
        try {
            validationService.validateClient(phone);
            if (clientService.createClient(name, surname, age, email, phone)) {
                System.out.println("Client was created");
            } else {
                System.out.println("Client wasn't created");
            }
        } catch (BusinessExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public long readId() throws IOException {
        System.out.print("Please enter id => ");
        String input;
        while (!(validationService.readInt(input = br.readLine()))) {
            System.out.println("Incorrect format of input value!");
            System.out.print("Please enter correct Number => ");
        }
        return Long.parseLong(input);
    }

    //TODO: add possibility to change not all fields of client
    protected void updateClient() throws IOException {
            System.out.print("Please enter new name => ");
            String newName = br.readLine();

            System.out.print("Please enter new second name => ");
            String surname = br.readLine();

            int age = inputAge();
            String email = inputEmail();
            String phone = inputPhone();

            if (clientService.updateClient(((ClientServiceImpl)clientService).getClientId(), newName, surname, age, email, phone)) {
                System.out.println("Client was updated");
            } else {
                System.out.println("Client wasn't updated");
            }

    }

    protected void showClientInfo() throws IOException {
        System.out.println("Info about client:");
        System.out.println(clientService.showClientInfo(((ClientServiceImpl)clientService).getClientId()).toString());
    }

}
