package com.michalgoly.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This Entity represents a single product in the store, which have its code,
 * a description and a price. 
 * 
 * @author Michal Goly
 */
@Entity
public class Product implements Serializable {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long productId;
   private String code;
   private String description;
   
   @Column(precision = 8, scale = 2)
   private BigDecimal price;
   
   public Product() {}

   public Long getProductId() {
      return productId;
   }

   public String getCode() {
      return code;
   }

   public String getDescription() {
      return description;
   }

   public BigDecimal getPrice() {
      return price;
   }
   
   public String getPriceCurrencyFormat() {
      NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);
      return currencyFormat.format(price.doubleValue());
   }
   
   public String getArtistName() {
      int hyphenIndex = description.indexOf("-");
      return description.substring(0, hyphenIndex).trim();
   }
   
   public String getAlbumName() {
      int hyphenIndex = description.indexOf("-");
      return description.substring(hyphenIndex + 1, description.length()).trim();
   }
   
   public String getImageUrl() {
      return "/img/" + code + "_cover.jpg";
   }
   
   public void setProductId(Long productId) {
      this.productId = productId;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

}
