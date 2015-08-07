package com.michalgoly.business;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Michal Goly
 */
@Entity
public class Invoice implements Serializable {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long invoiceNumber;
   
   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   private List<LineItem> lineItems;
   
   @ManyToOne
   private Customer customer;
   
   @Temporal(TemporalType.DATE)
   private Date invoiceDate;
   
   private boolean isProcessed;
   
   public Invoice() {}

   public Long getInvoiceNumber() {
      return invoiceNumber;
   }

   public List<LineItem> getLineItems() {
      return lineItems;
   }

   public Customer getCustomer() {
      return customer;
   }

   public Date getInvoiceDate() {
      return invoiceDate;
   }
   
   public double getInvoiceTotal() {
      double total = 0.0;
      for (LineItem i : lineItems) {
         total += i.getTotalPrice();
      }
      
      return total;
   }

   public String getInvoiceTotalCurrencyFormat() {
      NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);
      double total = getInvoiceTotal();
      return currencyFormat.format(total);
   }
   
   public boolean isIsProcessed() {
      return isProcessed;
   }

   public void setInvoiceNumber(Long invoiceNumber) {
      this.invoiceNumber = invoiceNumber;
   }

   public void setLineItems(List<LineItem> lineItems) {
      this.lineItems = lineItems;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public void setInvoiceDate(Date invoiceDate) {
      this.invoiceDate = invoiceDate;
   }

   public void setIsProcessed(boolean isProcessed) {
      this.isProcessed = isProcessed;
   }
}
