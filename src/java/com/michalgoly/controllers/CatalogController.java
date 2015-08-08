package com.michalgoly.controllers;

import com.michalgoly.business.Product;
import com.michalgoly.data.ProductDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Michal Goly
 */
@WebServlet(name = "CatalogController", urlPatterns = {"/catalog", "/catalog/product/*"})
public class CatalogController extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      
      System.out.println("TEST BEFORE");
      
      // Retrieve the uri
      String requestURL = request.getRequestURI();
      String url = "";
      
      System.out.println("TEST: " + requestURL);
      
      if (requestURL.endsWith("/catalog")) {
         url = displayProducts(request, response);
      }
      
      getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

   }

   private String displayProducts(HttpServletRequest request,
           HttpServletResponse response) {

      List<Product> products = ProductDB.selectProducts();
      HttpSession session = request.getSession();
      session.setAttribute("products", products);
      
      return "/catalog/products.jsp";
   }

}
