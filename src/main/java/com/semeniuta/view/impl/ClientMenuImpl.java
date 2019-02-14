package com.semeniuta.view.impl;

import com.semeniuta.domain.Order;
import com.semeniuta.domain.Product;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.services.impl.OrderServiceImpl;
import com.semeniuta.services.impl.ProductServiceImpl;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ClientMenuImpl implements Menu{
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //get bytes and convert it to string
    private final ProductService productService = new ProductServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private final static String[] menuItems = {"1. Show all products", "2. Create order", "3. Show all orders", "4. Return to main menu", "0. Exit"};

    @Override
    public void showMenu() throws IOException{
        boolean isRunning = true;

        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":
                    showProducts();
                    break;
                case "2":
                    createOrder();
                    break;
                case "3":
                    showOrders();
                    break;
                case "4":
                    new MainMenuImpl().showMenu();
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

    public  void showProducts(){
        System.out.println("All products:");
        List<Product> products = productService.showProducts();
        products.forEach(System.out::println);
    }

    public void createOrder() throws IOException{
        System.out.println("Enter product name:");
        String name = br.readLine();
        if(orderService.createOrder(name)){
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


}
