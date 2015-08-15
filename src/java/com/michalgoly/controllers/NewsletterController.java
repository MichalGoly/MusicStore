package com.michalgoly.controllers;

import com.michalgoly.business.Subscriber;
import com.michalgoly.data.SubscriberDB;
import java.io.IOException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michal Goly
 */
@WebServlet(name = "NewsletterController", urlPatterns = {"/newsletter/*"})
public class NewsletterController extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      doPost(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

      String url = "/subscribe/index.jsp";
      String requestURI = request.getRequestURI();

      if (requestURI.endsWith("/subscribe")) {
         url = subscribe(request, response);
      }

      getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   private String subscribe(HttpServletRequest request, HttpServletResponse response) {
      // retrieve parameters entered into the form
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");

      // input validation
      String url;
      String message = null;
      Subscriber subscriber = null;
      if (isValid(firstName, lastName, email)) {
         if (SubscriberDB.emailExists(email)) {
            subscriber = SubscriberDB.selectByEmail(email);
            subscriber.setFirstName(firstName);
            subscriber.setLastName(lastName);

            SubscriberDB.update(subscriber);
            url = "/subscribe/thanks.jsp";
         } else {
            subscriber = new Subscriber();
            subscriber.setFirstName(firstName);
            subscriber.setLastName(lastName);
            subscriber.setEmail(email);
            
            SubscriberDB.insert(subscriber);
            url = "/subscribe/thanks.jsp";
         }
      } else {
         message = "Some information you have enetered is not valid. Please try again.";
         url = "/subscribe/index.jsp";
      }
      
      request.setAttribute("message", message);
      request.setAttribute("subscriber", subscriber);
      return url;
   }

   private boolean isValid(String firstName, String lastName, String email) {
      boolean valid = true;
      if (firstName == null || lastName == null || email == null) {
         valid = false;
      } else if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
         valid = false;
      } else {
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
