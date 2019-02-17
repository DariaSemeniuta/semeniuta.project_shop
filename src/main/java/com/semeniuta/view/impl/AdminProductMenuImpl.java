package com.semeniuta.view.impl;

import com.semeniuta.domain.Product;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.services.impl.ProductServiceImpl;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

public class AdminProductMenuImpl implements Menu {

    private final static String[] menuItems = {"1. Create product", "2. Edit product", "3. Delete product", "4. Show all products", "5. Return to main menu", "0. Exit"};

    private final BufferedReader br;
    private final ProductService productService;

    public AdminProductMenuImpl(BufferedReader br, ProductService productService) {
        this.br = br;
        this.productService = productService;
    }

    @Override
    public void getUserResponse() throws IOException{
        System.out.println("product menu implementation is in progress");
        return;
        /*boolean isRunning = true;

        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input){
                case "1":
                    createProduct();
                    break;
                case "2":
                    editProduct();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "4":
                    showProducts();
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
        System.exit(0);*/
    }
    //TODO: add validation
    private void createProduct() throws IOException{
        System.out.println("Please enter name of the product => ");
        String name = br.readLine();

        System.out.println("Please enter price of the product => ");
        BigDecimal price;

        while(true) {
            try {
                price = new BigDecimal(br.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct (BigDecimal) price => ");
            }
        }
        if(productService.addProduct(name, price)){
            System.out.println("Product was added");
        }
        else{
            System.out.println("Product wasn't added");
        }

    }

    private void editProduct() throws IOException{
        System.out.println("Please enter name of the product that should be edited => ");
        long id = Long.getLong(br.readLine());

        System.out.println("Please enter new name of the product => ");
        String name = br.readLine();
        System.out.println("Please enter new price of the product => ");
        BigDecimal price;

        while(true) {
            try {
                price = new BigDecimal(br.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format of input value!");
                System.out.print("Please enter correct (BigDecimal) price => ");
            }
        }

        if(productService.editProduct(id, name, price)){
            System.out.println("Product was changed");
        }
        else{
            System.out.println("Product wasn't changed");
        }

    }

    private void deleteProduct() throws IOException{
        System.out.println("Please enter name of the product that should be deleted => ");
        long id = Long.getLong(br.readLine());
        if(productService.deleteProduct(id)){
            System.out.println("Product was deleted");
        }
        else{
            System.out.println("Product wasn't deleted");
        }
    }



}
