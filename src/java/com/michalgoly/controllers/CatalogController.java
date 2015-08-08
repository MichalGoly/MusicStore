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
      
      // Retrieve the uri
      String requestURI = request.getRequestURI();
      String url = "/index.jsp";
      
      if (requestURI.endsWith("/catalog")) {
         url = displayProducts(request, response);
      } else if (requestURI.matches("/musicStore/catalog/product/.*")) {
         url = showProduct(request, response);
      }
      
      getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

   }
   
   /*
   * Retrieves products list from the database, sets in as a session attribute
   * and prepares an appropriate url. 
   */
   private String displayProducts(HttpServletRequest request,
           HttpServletResponse response) {

      List<Product> products = ProductDB.selectProducts();
      HttpSession session = request.getSession();
      session.setAttribute("products", products);
      
      return "/catalog/products.jsp";
   }
   
   private String showProduct(HttpServletRequest request, 
           HttpServletResponse response) {
      
      // Retrieve the product code
      String requestURI = request.getRequestURI();
      String[] tokens = requestURI.split("/");
      String productCode = tokens[tokens.length - 1];
      
      // Retrieve the product from the database and set it as a session attribute
      Product product = ProductDB.selectProduct(productCode);
      HttpSession session = request.getSession();
      session.setAttribute("product", product);
      
      return "/catalog/single_product.jsp";
   }
   
}
