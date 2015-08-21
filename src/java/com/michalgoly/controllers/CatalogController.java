package com.michalgoly.controllers;

import com.michalgoly.business.Customer;
import com.michalgoly.business.Download;
import com.michalgoly.business.Product;
import com.michalgoly.data.CustomerDB;
import com.michalgoly.data.DownloadDB;
import com.michalgoly.data.ProductDB;
import com.michalgoly.util.CookieUtil;
import java.io.IOException;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
      } else if (requestURI.endsWith("/listen")) {
         url = listen(request, response);
      } else if (requestURI.matches("/MusicStore/catalog/product/.*")) {
         url = showProduct(request, response);
      }

      getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

      String requestURI = request.getRequestURI();
      String url = "/catalog";

      if (requestURI.endsWith("/register")) {
         url = registerCustomer(request, response);
      }

      getServletContext().getRequestDispatcher(url).forward(request, response);
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

   private String listen(HttpServletRequest request, HttpServletResponse response) {

      HttpSession session = request.getSession();
      Customer customer = (Customer) session.getAttribute("customer");

      if (customer == null) {
         Cookie[] cookies = request.getCookies();
         String email = CookieUtil.getCookieValue(cookies, "emailCookie");

         if (email == null) {
            return "/catalog/register.jsp";
         } else {
            customer = CustomerDB.selectByEmail(email);
         }

         // if customer with given email does not exist, redirect to registration
         if (customer == null) {
            return "/catalog/register.jsp";
         }
         session.setAttribute("customer", customer);
      }

      // keep track of downloads by the customers
      Product product = (Product) session.getAttribute("product");
      Download download = new Download();
      download.setCustomer(customer);
      download.setProduct(product);
      DownloadDB.insert(download);

      return "/catalog/listen.jsp";
   }

   private String registerCustomer(HttpServletRequest request,
           HttpServletResponse response) {

      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");

      // Validate input
      String message;
      if (!isValid(firstName, lastName, email)) {
         message = "Unfortunately some details you have entered are not valid.";
         request.setAttribute("message", message);
         return "/catalog/register.jsp";
      }

      Customer customer = new Customer();
      customer.setFirstName(firstName);
      customer.setLastName(lastName);
      customer.setEmail(email);

      if (CustomerDB.emailExists(email)) {
         CustomerDB.update(customer);
      } else {
         CustomerDB.insert(customer);
      }

      HttpSession session = request.getSession();
      session.setAttribute("customer", customer);

      Cookie emailCookie = new Cookie("emailCookie", email);
      emailCookie.setMaxAge(60 * 60 * 24 * 365 * 2);
      emailCookie.setPath("/");
      response.addCookie(emailCookie);

      // keep track of downloads by the customers
      Product product = (Product) session.getAttribute("product");
      Download download = new Download();
      download.setCustomer(customer);
      download.setProduct(product);
      DownloadDB.insert(download);
      
      return "/catalog/listen.jsp";
   }

   /* 
    * Validates data entered by user into a form
    * @return true if input is valid, false otherwise
    */
   private boolean isValid(String firstName, String lastName, String email) {
      boolean valid = true;

      if (firstName == null || firstName.isEmpty()) {
         valid = false;
      } else if (lastName == null || lastName.isEmpty()) {
         valid = false;
      } else {
         // validate email address
         try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
         } catch (AddressException e) {
            valid = false;
         }
      }

      return valid;
   }
}
