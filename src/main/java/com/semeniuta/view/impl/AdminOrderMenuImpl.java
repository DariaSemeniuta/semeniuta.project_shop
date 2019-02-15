package com.semeniuta.view.impl;

import com.semeniuta.services.OrderService;
import com.semeniuta.services.impl.OrderServiceImpl;
import com.semeniuta.view.Menu;

import java.io.IOException;


public class AdminOrderMenuImpl extends AdminMainMenuImpl {

private final static String[] menuItems = {"1. Create order", "2. Edit order status", "3. Delete order", "4. Show all orders", "5. Return to main menu", "0. Exit"};
private final OrderService orderService = new OrderServiceImpl();

    @Override
    public void getUserResponse() throws IOException{
        boolean isRunning = true;
        Menu menu = new  ClientMenuImpl();
        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":
                    ((ClientMenuImpl) menu).createOrder(); // why Idea change menu.createOrder() to ((ClientMenuImpl) menu).createOrder()?
                    break;
                case "2":
                    editOrderStatus();
                    break;
                case "3":
                    deleteOrder();
                    break;
                case "4":
                    ((ClientMenuImpl) menu).showOrders();
                    break;
                case "5":
                    new AdminMainMenuImpl().getUserResponse();
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

    private void editOrderStatus() throws IOException{
        System.out.println("Please enter orderId => ");
        long id;

        while(true) {
            try {
                id = new Long(br.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct (Long) orderId => ");
            }
        }
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
        long id;

        while(true) {
            try {
                id = new Long(br.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct (Long) orderId => ");
            }
        }
        if(orderService.deleteOrder(id)){
            System.out.println("Order was deleted");
        }
        else{
            System.out.println("Order wasn't deleted");
        }
    }

}
