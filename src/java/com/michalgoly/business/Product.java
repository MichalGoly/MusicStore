package com.michalgoly.business;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
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
   private BigInteger price;
   
   public Product() {}
   
   
   
}
