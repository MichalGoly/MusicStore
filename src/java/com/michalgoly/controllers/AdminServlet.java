package com.michalgoly.controllers;

import com.michalgoly.business.Download;
import com.michalgoly.business.Invoice;
import com.michalgoly.data.DownloadDB;
import com.michalgoly.data.InvoiceDB;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
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
      
      System.out.println(requestURI);
      
      if (requestURI.endsWith("/processInvoices")) {
         url = processInvoices(request, response);
      } else if (requestURI.endsWith("/displayLogs")) {
         url = displayLogs(request, response);
      } else if (requestURI.endsWith("/displayInvoice")) {
         url = displayInvoice(request, response);
      }
      
      getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      
      String requestURI = request.getRequestURI();
      String url = "/admin_panel/index.jsp";
      
      if (requestURI.endsWith("/processInvoice")) {
         url = processInvoice(request, response);
      } else if (requestURI.endsWith("/processInvoices")) {
         url = processInvoices(request, response);
      }
      
      getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   private String processInvoices(HttpServletRequest request, 
           HttpServletResponse response) {
      
      List<Invoice> invoices = InvoiceDB.selectInvoices();
      Collections.reverse(invoices);
      request.setAttribute("invoices", invoices);
      return "/admin_panel/invoices.jsp";
   }

   private String displayLogs(HttpServletRequest request,
           HttpServletResponse response) {
      
      List<Download> downloads = DownloadDB.selectDownloads();
      Collections.reverse(downloads);
      request.setAttribute("downloads", downloads);
      return "/admin_panel/downloads.jsp";
   }

   private String displayInvoice(HttpServletRequest request,
           HttpServletResponse response) {
      
      // retrieve invoice number
      String requestURI = request.getRequestURI();
      String[] tokens = requestURI.split("/");
      Long invoiceNumber = Long.parseLong(tokens[tokens.length - 2]);
      
      // get invoice based on its number
      Invoice invoice = InvoiceDB.select(invoiceNumber);
      request.setAttribute("invoice", invoice);
      
      return "/admin_panel/single_invoice.jsp";
   }

   private String processInvoice(HttpServletRequest request, 
           HttpServletResponse response) {
      
      // retrieve an appropriate invoice and make it processed
      Long invoiceNumber = Long.parseLong(request.getParameter("invoiceNumber"));
      Invoice invoice = InvoiceDB.select(invoiceNumber);
      invoice.setProcessed(true);
      InvoiceDB.update(invoice);
      
      return "/admin/processInvoices";
   }

}
