package com.michalgoly.business;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * This entity represents a collection of products with a specified quantity.
 * 
 * @author Michal Goly
 */
@Entity
public class LineItem implements Serializable {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long lineItemId;
   
   @OneToOne
   private Product product;
   
   private int quantity = 1;
   
   public LineItem() {}

   public Long getLineItemId() {
      return lineItemId;
   }

   public Product getProduct() {
      return product;
   }

   public int getQuantity() {
      return quantity;
   }
   
   public double getTotalPrice() {
      return product.getPrice().doubleValue() * quantity;
   } 
   
   public String getTotalPriceCurrencyFormat() {
      NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);
      return currencyFormat.format(getTotalPrice());
   }
   
   public void setLineItemId(Long lineItemId) {
      this.lineItemId = lineItemId;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
   
}
