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
 * This class represents an invoice which is generated when a customer makes
 * a purchase in the store.
 * 
 * @author Michal Goly
 */
@Entity
public class Invoice implements Serializable {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long number;
   
   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   private List<LineItem> lineItems;
   
   @ManyToOne
   private Customer customer;
   
   @Temporal(TemporalType.TIMESTAMP)
   private Date invoiceDate;
   
   private boolean processed;
   
   public Invoice() {}

   public Long getNumber() {
      return number;
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

   public boolean isProcessed() {
      return processed;
   }
   

   public void setNumber(Long number) {
      this.number = number;
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

   public void setProcessed(boolean processed) {
      this.processed = processed;
   }

}
