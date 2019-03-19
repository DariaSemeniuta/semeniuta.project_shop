package com.semeniuta.servlets;

import com.semeniuta.domain.Client;
import com.semeniuta.domain.Order;
import com.semeniuta.services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderServlet extends HttpServlet{

    private OrderService orderService;

    public OrderServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String path = req.getPathInfo();
        if("/delete".equals(path)){
            doDelete(req,resp);
            return;
        }
        if("/update".equals(path)){
            doPut(req,resp);
            return;
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        for (Order order:orderService.showOrders()) {
            writer.println("<p>"+order.toString()+"</p>");
            writer.println("<br>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String clientId = req.getParameter("clientId");
        String productIds = req.getParameter("productIds");
        productIds = productIds.replaceAll("[^0-9]+", " ");
        List<Long> products = new ArrayList<>();
        for (String id:Arrays.asList(productIds.trim().split(" "))) {
            products.add(Long.parseLong(id));
        }
        orderService.createOrder(products, Long.parseLong(clientId));
        writer.println("<h2>Successfully</h2><br><a href=\"admin/adminOrderMenu.html\">continue...</a>");

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        orderService.deleteOrder(Long.parseLong(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp){
        String id = req.getParameter("id");
        String status = req.getParameter("status");
        orderService.editOrderStatus(Long.parseLong(id),status);
    }
}
