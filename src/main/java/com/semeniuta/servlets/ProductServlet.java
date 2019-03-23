package com.semeniuta.servlets;

import com.semeniuta.domain.Product;
import com.semeniuta.exceptions.BusinessExceptions;
import com.semeniuta.services.ProductService;
import com.semeniuta.validators.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class ProductServlet extends HttpServlet {
    private ProductService productService;
    private ValidationService validationService;

    private String errorMessage = "<div id=\"error\" class=\"modalbackground\">\n" +
            "\t<div class=\"modalwindow\">\n" +
            "\t\t<h3>Error!</h3>\n" +
            "\t\t<p>Please enter valid price</p>\n" +
            "\t\t<a href=\"/admin/createProduct.html\">Ok</a>\n" +
            "\t</div>\n" +
            "</div>";

    public ProductServlet(ProductService productService, ValidationService validationService) {
        this.productService = productService;
        this.validationService = validationService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        if ("/delete".equals(path)) {
            doDelete(req, resp);
            return;
        }
        if ("/update".equals(path)) {
            doPut(req, resp);
            return;
        }
        if ("/getForOrder".equals(path)) {
            String response = "<select size=\"3\">";
            for (Product product : productService.showProducts()) {
                response += "<option value=\"" + product.getId() + "\">" + product.getName() + "  " + product.getPrice() + "</option>";
            }
            response += "<\\select>";
            writer.println(response);
        }
        for (Product product : productService.showProducts()) {
            writer.println("<p>" + product.toString() + "</p>");
        }
    }


    /*public BigDecimal checkPrice (String input, PrintWriter writer){
        try {
                validationService.validatePrice(input);
            }
            catch (BusinessExceptions e){
                writer.println(errorMessage);
            }
        return new BigDecimal(input);
    }*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        String name = req.getParameter("name");
        String inputPrice = req.getParameter("price");
        BigDecimal price = new BigDecimal(inputPrice); //checkPrice(inputPrice, writer);

        productService.addProduct(name, price);
        writer.println("<iframe src=\"/products\"></iframe>");

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        productService.deleteProduct(Long.parseLong(id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        productService.editProduct(Long.parseLong(id), name, new BigDecimal(price));
    }
}
