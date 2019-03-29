package com.semeniuta;

import com.semeniuta.view.Menu;
import com.semeniuta.view.impl.MainMenuImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class App {
    public static void main(String[] args) {
       /* BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ClientDao clientDao = new ClientDaoDBImpl();
        ClientService clientService = new ClientServiceImpl(clientDao);

        ProductDao productDao = new ProductDaoDBImpl();
        ProductService productService = new ProductServiceImpl(productDao);

        OrderDao orderDao = new OrderDaoDBImpl();
        OrderService orderService = new OrderServiceImpl(orderDao);


        ValidationService validationService = new ValidationServiceImpl(clientService, productService, orderService);

        Menu menu = new MainMenuImpl(br, clientService, productService, orderService, validationService);
        */
        /*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(("app.xml"));
        Menu menu = (MainMenuImpl) context.getBean("mainMenu");*/

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Menu menu = (MainMenuImpl) context.getBean(MainMenuImpl.class);
        try {
            menu.getUserResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
