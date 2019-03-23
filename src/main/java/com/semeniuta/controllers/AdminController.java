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
            //"\t\t<a href=\"/admin/logIn.jsp\">Ok</a>\n" +
            "\t</div>\n" +
            "</div>";

    @RequestMapping(value = "/adminLogIn", method = RequestMethod.POST)
    public ModelAndView  adminLogIn(
        @RequestParam String user,
        @RequestParam String password,
        ModelMap modelMap, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("client");
        String message;
        if ((adminLogName.equals(user)) && (adminPwd.equals(password))) {
            message = "Successfully";//<br><a href=\"admin/adminMenu.html\">continue...</a>";
        } else {
            message = "error";
        }
        modelMap.put("message", message);
        return modelAndView;
    }

}

