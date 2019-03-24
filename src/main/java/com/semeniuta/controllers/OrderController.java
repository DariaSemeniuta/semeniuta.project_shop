package com.semeniuta.controllers;

import com.semeniuta.domain.Order;
import com.semeniuta.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String showOrders(ModelMap modelMap, HttpServletRequest req) {
        String source = "";
        Object sessionClientId = req.getSession().getAttribute("userId");
        List<Order> result = null;
        if (sessionClientId != null) {
            List<Order> orders;
            if ((orders = orderService.showOrders()) != null) {
                result = orders.stream().filter((order -> order.getIdClient() == Long.parseLong(sessionClientId.toString()))).collect(Collectors.toList());
            }
        } else {
            result = orderService.showOrders();
        }
        for (Order order : result) {
            source += "<p>" + order.toString() + "</p>";
        }

        modelMap.addAttribute("message", "All orders");
        modelMap.addAttribute("source", source);
        return "out";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public String createOrder(@RequestParam String productIds,
                              ModelMap modelMap,
                              HttpServletRequest req) {
        productIds = productIds.replaceAll("[^0-9]+", " ");
        List<Long> products = new ArrayList<>();
        for (String id : Arrays.asList(productIds.trim().split(" "))) {
            products.add(Long.parseLong(id));
        }
        String clientId;
        Object sessionClientId = req.getSession().getAttribute("userId");
        if (sessionClientId != null) {
            clientId = sessionClientId.toString();
        } else {
            clientId = req.getParameter("clientId");
        }

        orderService.createOrder(products, Long.parseLong(clientId));
        showOrders(modelMap, req);
        return "out";
    }

    @RequestMapping(value = "/orders/delete", method = RequestMethod.POST)
    public String deleteOrder(@RequestParam String id,
                              ModelMap modelMap,
                              HttpServletRequest req) {
        orderService.deleteOrder(Long.parseLong(id));
        showOrders(modelMap, req);
        return "out";
    }

    @RequestMapping(value = "/orders/update", method = RequestMethod.POST)
    public String updateOrderStatus(@RequestParam String id,
                                    @RequestParam String status,
                                    ModelMap modelMap,
                                    HttpServletRequest req) {
        orderService.editOrderStatus(Long.parseLong(id), status);
        showOrders(modelMap, req);
        return "out";
    }

}
