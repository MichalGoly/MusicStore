package com.michalgoly.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controls the restricted, admin area of the website.
 * 
 * @author Michal Goly
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/*"})
public class AdminServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      
      String requestURI = request.getRequestURI();
      String url = "/admin_panel/index.jsp";
      
      if (requestURI.endsWith("/processInvoices")) {
         url = processInvoices(request, response);
      } else if (requestURI.endsWith("/manageProducts")) {
         url = manageProducts(request, response); 
      } else if (requestURI.endsWith("/displayLogs")) {
         url = displayLogs(request, response);
      }
      
      getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      
   }

   private String processInvoices(HttpServletRequest request, HttpServletResponse response) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   private String manageProducts(HttpServletRequest request, HttpServletResponse response) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

   private String displayLogs(HttpServletRequest request, HttpServletResponse response) {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

}
