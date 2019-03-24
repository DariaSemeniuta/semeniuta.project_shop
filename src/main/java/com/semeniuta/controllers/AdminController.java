package com.semeniuta.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    private final String adminLogName = "admin";
    private final String adminPwd = "admin";
    private String errorMessage = "<div id=\"error\" class=\"modalbackground\">\n" +
            "\t<div class=\"modalwindow\">\n" +
            "\t\t<h3>Error!</h3>\n" +
            "\t\t<p>Please enter valid user or password</p>\n" +
            //"\t\t<a href=\"/WEB-INF/jsp/admin/logIn.jsp\">Ok</a>\n" +
            "\t</div>\n" +
            "</div>";

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(@RequestParam String user, ModelMap modelMap){
        //ModelAndView modelAndView = new ModelAndView("client");
        if("client".equals(user)){
            //modelAndView=new ModelAndView("/client/clientStartPage");
            return "/client/clientStartPage";
        }
        if("admin".equals(user)){
            //modelAndView=new ModelAndView("/admin/logIn");
            modelMap.addAttribute("message", errorMessage);
            return "/admin/logIn";
        }
        return "redirect: /admin/logIn";
    }

    @RequestMapping(value = "/adminLogIn", method = RequestMethod.POST)
    public String  adminLogIn(
        @RequestParam String user,
        @RequestParam String password,
        ModelMap modelMap) {
        String message=null;
        if ((adminLogName.equals(user)) && (adminPwd.equals(password))) {
            return "/admin/adminMenu";
        } else {
            message = errorMessage;
            modelMap.put("message", message);
            return "/admin/logIn";
        }

    }

    @RequestMapping(value = "/menu",  method = RequestMethod.GET)
    public String getMenu(@RequestParam String menu){
        switch (menu){
            case "Client menu":
                return "/admin/adminClientMenu";

            case "Product menu":
                return "/admin/adminProductMenu";

            case "Order menu":
                return "/admin/adminOrderMenu";
            case "Create product":
                return "/admin/createProduct";
            case "Delete product":
                return "/admin/deleteProduct";
            case "Update product":
                return "/admin/updateProduct";
        }
        return "/admin/adminMenu";
    }




}

