package com.semeniuta.servlets;

import com.semeniuta.services.ClientService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientLogInServlet extends HttpServlet {

    private ClientService clientService;

    private String errorMessage = "<div id=\"error\" class=\"modalbackground\">\n" +
            "\t<div class=\"modalwindow\">\n" +
            "\t\t<h3>Error!</h3>\n" +
            "\t\t<p>Please enter valid phone and email</p>\n" +
            "\t\t<a href=\"/client/clientLogIn.html\">Ok</a>\n" +
            "\t</div>\n" +
            "</div>";

    public ClientLogInServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        if(clientService.isClientExist(phone)) {
            if (clientService.getClientEmailByPhone(phone).equals(email)) {
                //Todo: user should be taken from session
                HttpSession session = req.getSession();
                session.setAttribute("userId", clientService.getClientByPhone(phone).getId());
                writer.println("<h2>Successfully</h2><br><a href=\"client/clientMenu.html\">continue...</a>");

            }
        }
        else{
            writer.println(errorMessage);
        }

    }
}
