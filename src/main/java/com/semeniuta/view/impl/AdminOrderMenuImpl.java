package com.semeniuta.view.impl;

import com.semeniuta.domain.Order;
import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.services.impl.OrderServiceImpl;
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
    public void getUserResponse() throws IOException{
        boolean isRunning = true;
        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":
                    createOrder(); // why Idea change menu.createOrder() to ((ClientMenuImpl) menu).createOrder()?
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


    public long readId() throws IOException{
        System.out.print("Please enter id => ");
        String input;
        while(!(validationService.readInt(input = br.readLine()))){
            System.out.println("Incorrect format of input value!");
            System.out.print("Please enter correct Number => ");
        }
        return Long.parseLong(input);
    }

    public void createOrder() throws IOException{
        boolean flag = true;
        List<Long> ids = new ArrayList<>();
        System.out.println("Enter product ids:");
        while (br.readLine().equals("")) {
            long id = readId();

            while (flag) {
                try {
                    validationService.validateProductId(id);
                } catch (BusinessExceptions e) {
                    e.printStackTrace();
                }
            }
            ids.add(id);
        }

        if(orderService.createOrder(ids)){
            System.out.println("Order was added");
        }
        else {
            System.out.println("Order wasn't added");
        }
    }

    public void showOrders(){
        System.out.println("All orders");
        List<Order> orders = orderService.showOrders();
        orders.forEach(System.out::println);
    }
    private void editOrderStatus() throws IOException{
        System.out.println("Please enter orderId => ");

        long id = readId();
        System.out.println("Please enter new order status => ");
        String status = br.readLine();
        if(orderService.editOrderStatus(id, status)){
            System.out.println("Order status was changed");
        }
        else{
            System.out.println("Order status wasn't changed");
        }
    }

    private void deleteOrder() throws IOException{
        System.out.println("Please enter orderId => ");
        long id = readId();

        if(orderService.deleteOrder(id)){
            System.out.println("Order was deleted");
        }
        else{
            System.out.println("Order wasn't deleted");
        }
    }

}
