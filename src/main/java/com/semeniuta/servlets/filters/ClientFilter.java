package com.semeniuta.servlets.filters;

import com.semeniuta.validators.ValidationService;
import com.semeniuta.validators.ValidationServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
@WebFilter(urlPatterns = "/clients")
public class ClientFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        String age = servletRequest.getParameter("age");
        try{
            Integer.parseInt(age);

        }catch (NumberFormatException e){
            PrintWriter printWriter = servletResponse.getWriter();
            printWriter.println("<h2>Wrong Age</h2>");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
