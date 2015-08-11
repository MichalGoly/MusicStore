package com.michalgoly.controllers;

import com.michalgoly.business.Cart;
import com.michalgoly.business.Customer;
import com.michalgoly.business.LineItem;
import com.michalgoly.business.Product;
import com.michalgoly.data.CustomerDB;
import com.michalgoly.data.ProductDB;
import com.michalgoly.util.CookieUtil;
import java.io.IOException;
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
@WebServlet(name = "OrderController", urlPatterns = {"/order/*"})
public class OrderController extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      
      String requestURI = request.getRequestURI();
      String url = "/cart/cart.jsp";
      
      if (requestURI.endsWith("/showCart")) {
         url = showCart(request, response);
      }
      
      getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      
      String requestURI = request.getRequestURI();
      String url = "/cart/cart.jsp";
      
      if (requestURI.endsWith("/addItem")) {
         url = addItem(request, response);
      } else if (requestURI.endsWith("/removeItem")) {
         url = removeItem(request, response);
      } else if (requestURI.endsWith("/updateItem")) {
         url = updateItem(request, response);
      } else if (requestURI.endsWith("/checkCustomer")) {
            url = checkCustomer(request, response);
      }
      
      getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   private String showCart(HttpServletRequest request, HttpServletResponse response) {
      HttpSession session = request.getSession();
      Cart cart = (Cart) session.getAttribute("cart");

      if (cart == null || cart.getSize() <= 0) {
         String emptyMessage = "Your cart is empty";
         request.setAttribute("emptyMessage", emptyMessage);
      }
      
      return "/cart/cart.jsp";
   }

   private String addItem(HttpServletRequest request, HttpServletResponse response) {
      // retrieve or create a cart
      HttpSession session = request.getSession();
      Cart cart = (Cart) session.getAttribute("cart");
      if (cart == null) {
         cart = new Cart();
      }
      
      // get the product from the database, create a line item and put it into the cart
      String productCode = request.getParameter("productCode");
      Product product = ProductDB.selectProduct(productCode);
      if (product != null) {
         LineItem lineItem = new LineItem();
         lineItem.setProduct(product);
         cart.addItem(lineItem);
      }
      
      session.setAttribute("cart", cart);
      return "/cart/cart.jsp";
   }

   private String removeItem(HttpServletRequest request,
           HttpServletResponse response) {
      
      HttpSession session = request.getSession();
      Cart cart = (Cart) session.getAttribute("cart");
      String productCode = request.getParameter("productCode");
      Product product = ProductDB.selectProduct(productCode);
      
      if (cart != null && product != null) {
         LineItem lineItem = new LineItem();
         lineItem.setProduct(product);
         cart.removeItem(lineItem);
      }
      
      return "/cart/cart.jsp";
   }

   private String updateItem(HttpServletRequest request, 
           HttpServletResponse response) {
      
      HttpSession session = request.getSession();
      Cart cart = (Cart) session.getAttribute("cart");
      String productCode = request.getParameter("productCode");
      Product product = ProductDB.selectProduct(productCode);
      
      int quantity;
      try {
         quantity = Integer.parseInt(request.getParameter("quantity"));
         if (quantity < 0) {
            quantity = 1;
         }
      } catch (NumberFormatException e) {
         quantity = 1;
      }
      
      if (cart != null && product != null) {
         LineItem lineItem = new LineItem();
         lineItem.setProduct(product);
         lineItem.setQuantity(quantity);
         
         if (quantity > 0) {
            cart.addItem(lineItem);
         } else {
            cart.removeItem(lineItem);
         }
      }
      
      return "/cart/cart.jsp";
   }

   private String checkCustomer(HttpServletRequest request,
           HttpServletResponse response) {
      
      HttpSession session = request.getSession();
      Customer customer = (Customer) session.getAttribute("customer");
      
      String url = "/cart/new_customer.jsp";
      if (customer != null && !customer.getAddress().getAddress1().equals("")) {
         // customer with an address1 exists
         url = "/order/displayInvoice";
      } else {
         Cookie[] cookies = request.getCookies();
         String email = CookieUtil.getCookieValue(cookies, "emailCookie");
         
         if (email == null) {
            customer = new Customer();
            url = "/cart/new_customer.jsp";
         } else {
            customer = CustomerDB.selectByEmail(email);
            if (customer != null && !customer.getAddress().getAddress1().equals("")) {
               url = "/order/displayInvoice";
            }
         }
      }
      
      session.setAttribute("customer", customer);
      return url;
   }

}
