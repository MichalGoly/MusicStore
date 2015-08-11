package com.michalgoly.controllers;

import com.michalgoly.business.Cart;
import java.io.IOException;
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

}
