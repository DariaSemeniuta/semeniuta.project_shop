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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ClientServlet extends HttpServlet {

    private ClientService clientService;

    public ClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        if ("/delete".equals(path)) {
            doDelete(req, resp);
            return;
        }
        if ("/update".equals(path)) {
            doPut(req, resp);
            return;
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        Object clientId = req.getSession().getAttribute("userId");
        if (clientId != null) {
            Client client = clientService.showClientInfo(Long.parseLong(clientId.toString()));
            writer.println("<p>" + client.toString() + "</p>");
            writer.println("<br>");
        }
        for (Client client : clientService.showClients()) {
            writer.println("<p>" + client.toString() + "</p>");
            writer.println("<br>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        clientService.createClient(name, surname, Integer.parseInt(age), email, phone);

        String path = req.getPathInfo();
        if ("/registration".equals(path)) {
            registerUser(req, resp);
        }

        doGet(req, resp);
    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        clientService.createClient(name, surname, Integer.parseInt(age), email, phone);
        HttpSession session = req.getSession();
        session.setAttribute("userId", clientService.getClientByPhone(phone).getId());
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Successfully</h2><br><a href=\"client/clientMenu.html\">continue...</a>");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        clientService.deleteClient(Long.parseLong(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        Object clientId = req.getSession().getAttribute("userId");
        if (clientId != null) {
            id = clientId.toString();
        }
        clientService.updateClient(Long.parseLong(id), name, surname, Integer.parseInt(age), email, phone);
    }


}
