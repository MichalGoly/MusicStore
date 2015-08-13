package com.michalgoly.business;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This class represents a cart which can be filled with line items by a customer.
 *
 * @author Michal Goly
 */
public class Cart implements Serializable {

   private List<LineItem> items;

   public Cart() {
      items = new ArrayList<>();
   }

   public List<LineItem> getItems() {
      return items;
   }

   public void setItems(List<LineItem> items) {
      this.items = items;
   }

   /**
    * @return The amount of LineItem objects in the cart
    */
   public int getSize() {
      return items.size();
   }
   
   /**
    * @return The total price for the items in the cart 
    */
   public double getTotalPrice() {
      double total = 0.0;
      for (LineItem i : items) {
         total += i.getTotalPrice();
      }

      return total;
   }
   
   /**
    * @return Formatted String which represents the total price for a cart
    */
   public String getTotalPriceCurrencyFormat() {
      NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);
      double totalPrice = getTotalPrice();
      return currencyFormat.format(totalPrice);
   }

   /**
    * Adds a line item into the cart if it's not already there. Otherwise, the
    * quantity will be increased.
    *
    * @param lineItem The line item to be added to the cart
    */
   public void addItem(LineItem lineItem) {
      String code = lineItem.getProduct().getCode();
      int quantity = lineItem.getQuantity();

      for (LineItem i : items) {
         if (i.getProduct().getCode().equals(code)) {
            // already exists
            i.setQuantity(quantity);
            return;
         }
      }

      items.add(lineItem);
   }

   /**
    * Removes the item if it exists in the cart, does nothing otherwise
    *
    * @param lineItem The line item to be removed
    */
   public void removeItem(LineItem lineItem) {
      String code = lineItem.getProduct().getCode();
      for (int i = 0; i < items.size(); i++) {
         if (items.get(i).getProduct().getCode().equals(code)) {
            items.remove(i);
            return;
         }
      }
   }
}
