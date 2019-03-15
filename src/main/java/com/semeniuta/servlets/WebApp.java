package com.semeniuta.servlets;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.dao.impl.ClientDaoDBImpl;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.impl.ClientServiceImpl;
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

       ServletContext servletContext =  sce.getServletContext();
       servletContext
               .addServlet("ClientServlet", new ClientServlet(clientService))
               .addMapping("/clients/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
