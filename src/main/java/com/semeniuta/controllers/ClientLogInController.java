package com.semeniuta.controllers;

import com.semeniuta.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ClientLogInController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/clientLogIn", method = RequestMethod.POST)
    public String clientLogIn(@RequestParam String phone,
                              @RequestParam String email,
                              ModelMap modelMap,
                              HttpServletRequest req) {
        if (clientService.isClientExist(phone)) {
            if (clientService.getClientEmailByPhone(phone).equals(email)) {
                HttpSession session = req.getSession();
                session.setAttribute("userId", clientService.getClientByPhone(phone).getId());
                return "/client/clientMenu";
            }
        } else {
            modelMap.put("message", "Incorrect phone or email");
            return "out";
        }
        return "/client/clientStartPage";
    }

    @RequestMapping(value = "clients/registration", method = RequestMethod.POST)
    public String clientRegistration(@RequestParam String name,
                                     @RequestParam String surname,
                                     @RequestParam String age,
                                     @RequestParam String phone,
                                     @RequestParam String email,
                                     HttpServletRequest req
    ) {
        clientService.createClient(name, surname, Integer.parseInt(age), email, phone);
        HttpSession session = req.getSession();
        session.setAttribute("userId", clientService.getClientByPhone(phone).getId());
        return "/client/clientMenu";
    }

    @RequestMapping(value = "/clientMenu", method = RequestMethod.GET)
    public String getMenu(@RequestParam String menu) {
        switch (menu) {
            case "Edit your profile":
                return "/client/updateProfile";
            case "Create orders":
                return "/client/createOrder";
            case "Registration":
                return "/client/clientRegistration";
            case "Log in":
                return "/client/clientLogIn";

        }
        return "/client/clientStartPage";
    }
}
