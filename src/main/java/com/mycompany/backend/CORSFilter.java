/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backend;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Khalil
 */
@WebFilter("/*")
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
        HttpServletResponse rr = (HttpServletResponse) resp;
        rr.addHeader("Access-Control-Allow-Origin", "*");
        rr.addHeader("Access-Control-Allow-Credentials", "true");
        rr.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        rr.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
        fc.doFilter(sr, resp);
    }

    @Override
    public void destroy() {

    }

    public CORSFilter() {
        System.out.println("bonjour");
    }
}
