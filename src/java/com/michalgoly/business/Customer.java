package com.michalgoly.business;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * This entity represents a single customer in the store. Each customer has
 * its name, email address and a credit card. 
 * 
 * @author Michal Goly
 */
@Entity
public class Customer implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long customerId;

   private String firstName;
   private String lastName;
   private String email;

   @OneToOne(cascade = CascadeType.ALL)
   private Address address;
   
   @OneToOne(cascade = CascadeType.ALL)
   private CreditCard creditCard;
   
   public Customer() {}

   public Long getCustomerId() {
      return customerId;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getEmail() {
      return email;
   }

   public Address getAddress() {
      return address;
   }

   public CreditCard getCreditCard() {
      return creditCard;
   }

   public void setCustomerId(Long customerId) {
      this.customerId = customerId;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   public void setCreditCard(CreditCard creditCard) {
      this.creditCard = creditCard;
   }
   
}
