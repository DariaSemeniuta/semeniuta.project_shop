package com.semeniuta.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(urlPatterns = "/adminLogIn")
public class AdminServlet extends HttpServlet{
    private final String adminLogName = "admin";
    private final String adminPwd = "admin";
    private String errorMessage = "<div id=\"error\" class=\"modalbackground\">\n" +
            "\t<div class=\"modalwindow\">\n" +
            "\t\t<h3>Error!</h3>\n" +
            "\t\t<p>Please enter valid user or password</p>\n" +
            "\t\t<a href=\"/admin/logIn.html\">Ok</a>\n" +
            "\t</div>\n" +
            "</div>";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String user = req.getParameter("user");
        String pwd = req.getParameter("password");
        if((adminLogName.equals(user))&&(adminPwd.equals(pwd))){
            writer.println("<h2>Successfully</h2><br><a href=\"admin/adminMenu.html\">continue...</a>");
        }
        else{
            writer.println(errorMessage);
        }

    }

}
