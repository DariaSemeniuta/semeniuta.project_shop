package com.semeniuta.controllers;

import com.semeniuta.domain.Client;
import com.semeniuta.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String showClients(ModelMap modelMap, HttpServletRequest req) {
        Object clientId = req.getSession().getAttribute("userId");
        String source = "";
        if (clientId != null) {
            Client client = clientService.showClientInfo(Long.parseLong(clientId.toString()));
            source += client.toString();
        } else {
            for (Client client : clientService.showClients()) {
                source += "<p>" + client.toString() + "</p>";
            }
        }
        modelMap.addAttribute("message", "All clients");
        modelMap.put("source", source);
        return "out";
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public String createClient(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String age,
            @RequestParam String phone,
            @RequestParam String email,
            ModelMap modelMap,
            HttpServletRequest req) {
        showClients(modelMap, req);
        return "out";
    }

    @RequestMapping(value = "/clients/delete", method = RequestMethod.POST)
    public String deleteClient(@RequestParam String id,
                               ModelMap modelMap,
                               HttpServletRequest req) {
        clientService.deleteClient(Long.parseLong(id));
        showClients(modelMap, req);
        return "out";
    }

    @RequestMapping(value = "/clients/update", method = RequestMethod.POST)
    public String updateClient(@RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String age,
                               @RequestParam String phone,
                               @RequestParam String email,
                               ModelMap modelMap,
                               HttpServletRequest req) {
        String id;
        Object clientId = req.getSession().getAttribute("userId");
        if (clientId != null) {
            id = clientId.toString();
        } else {
            id = req.getParameter("id");
        }
        clientService.updateClient(Long.parseLong(id), name, surname, Integer.parseInt(age), email, phone);
        showClients(modelMap, req);
        return "out";
    }

}
