package com.semeniuta.controllers;

import com.semeniuta.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.annotation.WebServlet;

@Controller
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public String showClients(ModelMap modelMap) {
        modelMap.put("message", clientService.showClients());
        return "client";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createClient(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String age,
            @RequestParam String phone,
            @RequestParam String email,
            ModelMap modelMap) {
        modelMap.put("message", clientService.createClient(name, surname, Integer.parseInt(age),phone,email));
        return "clients";
    }

}
