package com.semeniuta.view.impl;

import com.semeniuta.domain.Order;
import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.OrderService;
import com.semeniuta.validators.ValidationService;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AdminOrderMenuImpl implements Menu {

    private final static String[] menuItems = {"1. Create order", "2. Edit order status", "3. Delete order", "4. Show all orders", "5. Return to main menu", "0. Exit"};

    private final BufferedReader br;
    private final OrderService orderService;
    private final ValidationService validationService;

    public AdminOrderMenuImpl(BufferedReader br, OrderService orderService, ValidationService validationService) {
        this.br = br;
        this.orderService = orderService;
        this.validationService = validationService;
    }

    @Override
    public void getUserResponse() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input) {
                case "1":
                    createOrder();
                    break;
                case "2":
                    editOrderStatus();
                    break;
                case "3":
                    deleteOrder();
                    break;
                case "4":
                    showOrders();
                    break;
                case "5":
                    return;
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


    public long readId() throws IOException {
        System.out.print("Please enter id => ");
        String input;
        while (!(validationService.readInt(input = br.readLine()))) {
            System.out.println("Incorrect format of input value!");
            System.out.print("Please enter correct Number => ");
        }
        return Long.parseLong(input);
    }

    private List<Long> readProductIds() throws IOException {
        String input;
        List<Long> productIds = new ArrayList<>();
        System.out.println("Please enter product ids(use Enter for separating):");
        while (!(input=br.readLine()).isEmpty()) {
            while (!(validationService.readInt(input))) {
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct Number => ");
            }
            long id = Long.parseLong(input);
            try {
                    validationService.validateProductId(id);
                    productIds.add(id);
                } catch (BusinessExceptions e) {
                    System.out.println(e.getMessage());
                System.out.print("Please enter correct product id => ");

            }
        }
        return productIds;
    }

    public void createOrder() throws IOException {
        List<Long> ids = readProductIds();
        if(ids.size()==0){
            System.out.println("You didn't put any products to order");
            return;
        }
        System.out.println("Enter client info:");
        long idClient = readId();
            try {
                validationService.validateClientId(idClient);
                if (orderService.createOrder(ids, idClient)) {
                    System.out.println("Order was added");
                } else {
                    System.out.println("Order wasn't added");
                }
            }catch (BusinessExceptions e){
                System.out.println(e.getMessage());

            }

    }

    public void createOrder(long clientId) throws IOException {
        List<Long> ids = readProductIds();
        if(ids.size()==0){
            System.out.println("You didn't put any products to order");
            return;
        }
        if (orderService.createOrder(ids, clientId)) {
                System.out.println("Order was added");
            } else {
                System.out.println("Order wasn't added");
            }
    }

    public void showOrders() {
        System.out.println("All orders:");
        List<Order> orders = orderService.showOrders();
        orders.forEach(System.out::println);
    }

    public void showOrders(long clientId) {
        System.out.println("All orders:");
        List<Order> orders = orderService.showOrders();
        orders.stream().filter((order -> order.getIdClient()==clientId)).forEach(System.out::println);
    }

    private void editOrderStatus() throws IOException {
        System.out.println("Please Order info:");
        long id = readId();
        try{
            validationService.validateOrderId(id);
            System.out.println("Please enter new order status => ");
            String status = br.readLine();
            if (orderService.editOrderStatus(id, status)) {
                System.out.println("Order status was changed");
            } else {
                System.out.println("Order status wasn't changed");
            }
        }catch (BusinessExceptions e){
            System.out.println(e.getMessage());
        }

    }

    private void deleteOrder() throws IOException {
        System.out.println("Please Order info:");
        long id = readId();
        try {
            validationService.validateOrderId(id);
            if (orderService.deleteOrder(id)) {
                System.out.println("Order was deleted");
            } else {
                System.out.println("Order wasn't deleted");
            }
        }catch (BusinessExceptions e){
            System.out.println(e.getMessage());
        }
    }

}
