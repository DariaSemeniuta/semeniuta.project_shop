package com.semeniuta.controllers;

import com.semeniuta.domain.Product;
import com.semeniuta.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller

public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String showProducts(ModelMap modelMap) {
        String message = "All products";
        String source = "";
        for (Product product : productService.showProducts()) {
            source += product.toString() + "<br>";
        }

        modelMap.addAttribute("message", message);
        modelMap.addAttribute("source", source);
        return "out";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String createProducts(@RequestParam String name,
                                 @RequestParam String price,
                                 ModelMap modelMap) {
        productService.addProduct(name, new BigDecimal(price));
        String message = "Product was successfully created";
        modelMap.addAttribute("message", message);
        showProducts(modelMap);
        return "out";
    }

    @RequestMapping(value = "/products/delete", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam String id,
                                ModelMap modelMap) {
        productService.deleteProduct(Long.parseLong(id));
        String message = "Product was successfully updated";
        modelMap.addAttribute("message", message);
        showProducts(modelMap);
        return "out";
    }


    @RequestMapping(value = "/products/update", method = RequestMethod.POST)
    public String updateProduct(@RequestParam String id,
                                @RequestParam String name,
                                @RequestParam String price,
                                ModelMap modelMap) {
        productService.editProduct(Long.parseLong(id), name, new BigDecimal(price));
        showProducts(modelMap);
        return "out";
    }


}
