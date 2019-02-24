package com.semeniuta.view.impl;

import com.semeniuta.domain.Product;
import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ProductService;;
import com.semeniuta.validators.ValidationService;
import com.semeniuta.view.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class AdminProductMenuImpl implements Menu {

    private final static String[] menuItems = {"1. Create product", "2. Edit product", "3. Delete product", "4. Show all products", "5. Return to main menu", "0. Exit"};

    private final BufferedReader br;
    private final ProductService productService;
    private final ValidationService validationService;


    public AdminProductMenuImpl(BufferedReader br, ProductService productService, ValidationService validationService) {
        this.br = br;
        this.productService = productService;
        this.validationService = validationService;
    }

    @Override
    public void getUserResponse() throws IOException {
        System.out.println("product menu implementation is in progress");
        boolean isRunning = true;

        while (isRunning) {
            this.showMenuItems(menuItems);
            String input = br.readLine();
            switch (input) {
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

    private BigDecimal readPrice() throws IOException{
        boolean flag = true;
        String input="0";
        while (flag){
            System.out.println("Please enter price of the product => ");
            try {
                validationService.validatePrice(input = br.readLine());
                flag = false;
            }
            catch (BusinessExceptions e){
                System.out.println("Please enter correct price!");
            }
        }
        return new BigDecimal(input);
    }

    private void createProduct() throws IOException {
        System.out.println("Please enter name of the product => ");
        String name = br.readLine();

        BigDecimal price = readPrice();

        if (productService.addProduct(name, price)) {
            System.out.println("Product was added");
        } else {
            System.out.println("Product wasn't added");
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

    private void editProduct() throws IOException {
        System.out.println("Please enter id of the product that should be edited => ");
        long id = readId();
        try {
            validationService.validateProductId(id);

            System.out.println("Please enter new name of the product => ");
            String name = br.readLine();
            System.out.println("Please enter new price of the product => ");
            BigDecimal price = readPrice();

            if (productService.editProduct(id, name, price)) {
                System.out.println("Product was changed");
            } else {
                System.out.println("Product wasn't changed");
            }
        }
        catch (BusinessExceptions e){
            System.out.println(e.getMessage());
        }

    }

    private void deleteProduct() throws IOException {
        System.out.println("Please enter id of the product that should be deleted => ");
        long id = readId();
        try{
            validationService.validateProductId(id);
            if (productService.deleteProduct(id)) {
                System.out.println("Product was deleted");
            } else {
                System.out.println("Product wasn't deleted");
            }

        }catch (BusinessExceptions e){
            System.out.println(e.getMessage());
        }

    }

    public void showProducts() {
        System.out.println("All products:");
        List<Product> products = productService.showProducts();
        products.forEach(System.out::println);
    }


}
