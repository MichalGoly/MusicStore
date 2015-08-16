package com.michalgoly.controllers;

import com.michalgoly.business.Address;
import com.michalgoly.business.CardType;
import com.michalgoly.business.Cart;
import com.michalgoly.business.CreditCard;
import com.michalgoly.business.Customer;
import com.michalgoly.business.Invoice;
import com.michalgoly.business.LineItem;
import com.michalgoly.business.Product;
import com.michalgoly.data.CustomerDB;
import com.michalgoly.data.InvoiceDB;
import com.michalgoly.data.ProductDB;
import com.michalgoly.util.CookieUtil;
import com.michalgoly.util.MailUtil;
import java.io.IOException;
import java.util.Date;
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
      } else if (requestURI.endsWith("/processCustomer")) {
         url = processCustomer(request, response);
      } else if (requestURI.endsWith("/displayInvoice")) {
         url = displayInvoice(request, response);
      } else if (requestURI.endsWith("/displayCustomer")) {
         url = "/cart/new_customer.jsp";
      } else if (requestURI.endsWith("/displayPayment")) {
         url = "/cart/payment.jsp";
      } else if (requestURI.endsWith("/complete")) {
         url = complete(request, response);
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
      if (customer != null && customer.getAddress() != null) {
         // customer with an address exists
         url = "/order/displayInvoice";
      } else {
         Cookie[] cookies = request.getCookies();
         String email = CookieUtil.getCookieValue(cookies, "emailCookie");

         if (email == null) {
            customer = new Customer();
            url = "/cart/new_customer.jsp";
         } else {
            customer = CustomerDB.selectByEmail(email);
            if (customer != null && customer.getAddress() != null) {
               url = "/order/displayInvoice";
            }
         }
      }

      session.setAttribute("customer", customer);
      return url;
   }

   private String processCustomer(HttpServletRequest request,
           HttpServletResponse response) {

      // retrieve data entered by the customer
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String email = request.getParameter("email");
      String companyName = request.getParameter("companyName");
      String address1 = request.getParameter("address1");
      String address2 = request.getParameter("address2");
      String city = request.getParameter("city");
      String county = request.getParameter("county");
      String postCode = request.getParameter("postCode");
      String country = request.getParameter("country");

      HttpSession session = request.getSession();
      Customer customer = createCustomer(session, firstName, lastName, email,
              companyName, address1, address2, city, county, postCode, country);

      // validate data
      String url;
      if (isValid(firstName, lastName, email, companyName, address1, address2,
              city, county, postCode, country)) {

         if (CustomerDB.emailExists(email)) {
            CustomerDB.update(customer);
         } else {
            CustomerDB.insert(customer);
         }
         url = "/order/displayInvoice";
      } else {
         String message = "Unfortunately you either left some fields blank, or "
                 + "the information provided is not valid.";
         request.setAttribute("message", message);
         url = "/cart/new_customer.jsp";
      }

      session.setAttribute("customer", customer);
      return url;
   }

   /* 
    * Validates data entered by user into a form
    * @return true if input is valid, false otherwise
    */
   private boolean isValid(String firstName, String lastName, String email,
           String companyName, String address1, String address2, String city,
           String county, String postCode, String country) {

      boolean valid = true;

      if (firstName == null || lastName == null || email == null || address1 == null
              || city == null || county == null || postCode == null || country == null) {
         valid = false;
      } else if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()
              || address1.isEmpty() || city.isEmpty() || county.isEmpty()
              || postCode.isEmpty() || country.isEmpty()) {
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

   /*
    * Retrieves a customer object from the http session or creates a new one if
    * it does not exist. Afterwards, it fills it with the provided data. 
    */
   private Customer createCustomer(HttpSession session, String firstName,
           String lastName, String email, String companyName, String address1,
           String address2, String city, String county, String postCode,
           String country) {

      Customer customer = (Customer) session.getAttribute("customer");
      if (customer == null) {
         customer = new Customer();
      }

      customer.setFirstName(firstName);
      customer.setLastName(lastName);
      customer.setEmail(email);

      Address address = new Address();
      address.setAddress1(address1);
      address.setAddress2(address2);
      address.setCompanyName(companyName);
      address.setCity(city);
      address.setCounty(county);
      address.setCountry(country);
      address.setPostCode(postCode);

      customer.setAddress(address);
      return customer;
   }

   private String displayInvoice(HttpServletRequest request,
           HttpServletResponse response) {

      HttpSession session = request.getSession();
      Customer customer = (Customer) session.getAttribute("customer");
      Cart cart = (Cart) session.getAttribute("cart");

      Invoice invoice = new Invoice();
      invoice.setCustomer(customer);
      invoice.setLineItems(cart.getItems());
      invoice.setInvoiceDate(new Date());

      session.setAttribute("invoice", invoice);

      return "/cart/invoice.jsp";
   }

   private String complete(HttpServletRequest request, HttpServletResponse response) {
      // retrieve parametres from the payment form
      String cardType = request.getParameter("cardType");
      String cardNumber = request.getParameter("cardNumber");
      String expirationMonth = request.getParameter("expirationMonth");
      String expirationYear = request.getParameter("expirationYear");

      HttpSession session = request.getSession();
      Customer customer = (Customer) session.getAttribute("customer");
      Invoice invoice = (Invoice) session.getAttribute("invoice");

      CreditCard creditCard = new CreditCard();
      creditCard.setCardType(CardType.fromString(cardType));
      creditCard.setNumber(cardNumber);
      creditCard.setExpirationDate(expirationMonth + "/" + expirationYear);

      customer.setCreditCard(creditCard);
      if (CustomerDB.emailExists(customer.getEmail())) {
         CustomerDB.update(customer);
      } else {
         CustomerDB.insert(customer);
      }

      // insert an invoice to the database and remove all items from user's cart
      InvoiceDB.insert(invoice);
      session.setAttribute("cart", null);
      
      Cookie emailCookie = new Cookie("emailCookie", customer.getEmail());
      emailCookie.setMaxAge(60 * 24 * 365 * 2 * 60);
      emailCookie.setPath("/");
      response.addCookie(emailCookie);
      
      // send confirmation email
      MailUtil.sendOrderConfirmation(getServletContext(), customer.getEmail(),
              customer.getFirstName());
      
      return "/cart/thanks.jsp";
   }

}
