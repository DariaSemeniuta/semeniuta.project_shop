package com.semeniuta.view.impl;

import com.semeniuta.domain.Order;
import com.semeniuta.domain.Product;
import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.validators.ValidationService;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ClientMenuImpl extends MainMenuImpl{

    private final static String[] menuItems = {"1. Register new user", "2. Edit your profile", "3. Show user profile", "4. Show all products", "5. Create order", "6. Show all orders", "7. Return to main menu", "0. Exit"};

    public ClientMenuImpl(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService, ValidationService validationService) {
        super(br, clientService, productService, orderService, validationService);
    }

    private AdminOrderMenuImpl orderMenu = new AdminOrderMenuImpl(br, orderService,validationService);
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
                    showClientInfo();
                    break;
                case "4":
                    showProducts();
                    break;
                case "5":
                    orderMenu.createOrder();
                    break;
                case "6":
                    orderMenu.showOrders();
                    break;
                case "7":
                    super.getUserResponse();
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


    public int inputAge() throws IOException{
        boolean flag = true;
        int age=0;

        while(flag) {
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
                e.printStackTrace();
            }
        }
        return  age;
    }

    public String inputEmail() throws IOException{
        boolean flag = true;
        String email = "";

        while (flag){
            System.out.print("Please enter email => ");
            try {
                validationService.readEmail(email = br.readLine());
                flag = false;
            }catch (BusinessExceptions e){
                e.printStackTrace();
            }
        }
        return email;
    }


    public String inputPhone() throws IOException{
        boolean flag = true;
        String phone = "";

        while (flag){
            System.out.print("Please enter phone number => ");
            try {
                validationService.readPhone(phone = br.readLine());
                flag = false;
            }catch (BusinessExceptions e){
                e.printStackTrace();
            }
        }
        return phone;
    }

    protected void createClient() throws IOException{
        System.out.print("Please enter name => ");
        String name = br.readLine();

        System.out.print("Please enter second name => ");
        String surname = br.readLine();
        int age = inputAge();

        String email = inputEmail();
        String phone = inputPhone();
        try{
            validationService.validateClient(phone);
            if(clientService.createClient(name, surname, age, email, phone)){
                System.out.println("Client was created");
            }
            else{
                System.out.println("Client wasn't created");
            }
        }catch(BusinessExceptions e){
            e.printStackTrace();
        }



    }

    public long readId() throws IOException{
        System.out.print("Please enter id => ");
        String input;
        while(!(validationService.readInt(input = br.readLine()))){
            System.out.println("Incorrect format of input value!");
            System.out.print("Please enter correct Number => ");
        }
        return Long.getLong(input);
    }

    protected void updateClient() throws IOException{
        System.out.print("Please enter your id => ");
        long id = readId();
        System.out.print("Please enter new name => ");
        String newName = br.readLine();

        System.out.print("Please enter new second name => ");
        String surname = br.readLine();

        System.out.print("Please enter new age => ");
        int age = inputAge();

        System.out.print("Please enter new email => ");
        String email = inputEmail();

        System.out.print("Please enter new phone number => ");
        String phone = inputPhone();

        if(clientService.updateClient(id, newName, surname, age, email, phone)){
            System.out.println("Client was updated");
        }
        else{
            System.out.println("Client wasn't updated");
        }
    }

    protected void showClientInfo() throws IOException{
        System.out.println("Info about client:");
        long id = readId();
        clientService.showClientInfo(id);
    }


    public void showProducts(){
        System.out.println("All products:");
        List<Product> products = productService.showProducts();
        products.forEach(System.out::println);
    }




}
