package com.semeniuta.servlets;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.dao.OrderDao;
import com.semeniuta.dao.ProductDao;
import com.semeniuta.dao.impl.ClientDaoDBImpl;
import com.semeniuta.dao.impl.OrderDaoDBImpl;
import com.semeniuta.dao.impl.ProductDaoDBImpl;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.OrderService;
import com.semeniuta.services.ProductService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.semeniuta.services.impl.OrderServiceImpl;
import com.semeniuta.services.impl.ProductServiceImpl;
import com.semeniuta.servlets.filters.ClientFilter;
import com.semeniuta.validators.ValidationService;
import com.semeniuta.validators.ValidationServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApp implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ClientDao clientDao = new ClientDaoDBImpl();
        ClientService clientService = new ClientServiceImpl(clientDao);
        ProductDao productDao = new ProductDaoDBImpl();
        ProductService productService = new ProductServiceImpl(productDao);
        OrderDao orderDao = new OrderDaoDBImpl();
        OrderService orderService = new OrderServiceImpl(orderDao);

        ValidationService validationService = new ValidationServiceImpl(clientService,productService,orderService);
       ServletContext servletContext =  sce.getServletContext();
       servletContext
               .addServlet("ClientServlet", new ClientServlet(clientService))
               .addMapping("/clients/*");
        servletContext
                .addServlet("AdminServlet", new AdminServlet())
                .addMapping("/adminLogIn");
        servletContext
                .addServlet("ProductServlet", new ProductServlet(productService, validationService))
                .addMapping("/products/*");
        servletContext
                .addServlet("OrderServlet", new OrderServlet(orderService))
                .addMapping("/orders/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
