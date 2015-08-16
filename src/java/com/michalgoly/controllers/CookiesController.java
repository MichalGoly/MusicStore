package com.michalgoly.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Enables the user to delete persistent cookies stored in his browser.
 * 
 * @author Michal Goly
 */
@WebServlet(name = "CookiesController", urlPatterns = {"/cookies/*"})
public class CookiesController extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      
      String requestURI = request.getRequestURI();
      String url = "/";
      
      if (requestURI.endsWith("/delete")) {
         url = delete(request, response);
      }
      
      getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      doGet(request, response);
   }

   private String delete(HttpServletRequest request, HttpServletResponse response) {
      Cookie[] cookies = request.getCookies();
      for (Cookie c : cookies) {
         c.setMaxAge(0);
         c.setPath("/");
         response.addCookie(c);
      }
      return "/deleted_cookies.jsp";
   }

}