package com.michalgoly.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

/**
 * Utility class responsible for sending emails to customers using the JavaMail API
 * via Gmail SMPT Server.
 *
 * @author Michal Goly
 */
public class MailUtil {

   // You have to change these accordingly to make email work. You will also
   // most likely have to allow less secure apps to access your gmail for this
   // to work see https://support.google.com/accounts/answer/6010255?hl=en
   private static final String FROM = "username@gmail.com";
   private static final String USERNAME = "username";
   private static final String PASSWORD = "password";

   /**
    * Sends a confirmation email to the specified address
    *
    * @param sc The servlet context
    * @param to The email address of the receiver
    * @param customerName First name of the customer
    */
   public static void sendOrderConfirmation(ServletContext sc, String to, 
           String customerName) {
      String path = sc.getInitParameter("javaMailProperties");
      Properties props = new Properties();
      try (InputStream in = sc.getResourceAsStream(path)) {
         props.load(in);
      } catch (IOException e) {
         System.err.println("Failed to load JavaMail properties");
      }

      Session session = Session.getInstance(props, new Authenticator() {

         @Override
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(USERNAME, PASSWORD);
         }
      });
      
      try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(FROM));
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message.setSubject("Music Store order confirmation");
         message.setText("Dear " + customerName + ",\n\n Thank you for your interest " +
            "in our products. Your order has been received and will be processed " +
            "once payment has been confirmed.\n\nThe Music Store");
         
         Transport.send(message);
      } catch (MessagingException e) {
         System.err.println("Failed to send the order confirmation email:\n " + e);
      }
   }

}
