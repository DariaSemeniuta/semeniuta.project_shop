package com.semeniuta.servlets;

import com.semeniuta.dao.ClientDao;
import com.semeniuta.dao.impl.ClientDaoDBImpl;
import com.semeniuta.domain.Client;
import com.semeniuta.services.ClientService;
import com.semeniuta.services.impl.ClientServiceImpl;
import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/clients")
public class ClientServlet extends HttpServlet {

    private ClientDao clientDao = new ClientDaoDBImpl();
    private ClientService clientService = new ClientServiceImpl(clientDao);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        clientService.showClients();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        for (Client client:clientService.showClients()) {
            writer.println("<h1>"+client+"</h1>");

        }
    }
}
