package com.michalgoly.business;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This Entity represents a credit card which has its type (Visa, MasterCard), 
 * a number and an expiration date. 
 * 
 * @author Michal Goly
 */
@Entity
public class CreditCard implements Serializable {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long creditCardId;
   
   @Enumerated
   private CardType cardType;
   
   private String number;
   private String expirationDate;
   
   public CreditCard() {}
   
   public Long getCreditCardId() {
      return creditCardId;
   }

   public CardType getCardType() {
      return cardType;
   }

   public String getNumber() {
      return number;
   }

   public String getExpirationDate() {
      return expirationDate;
   }

   public void setCreditCardId(Long creditCardId) {
      this.creditCardId = creditCardId;
   }

   public void setCardType(CardType cardType) {
      this.cardType = cardType;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   public void setExpirationDate(String expirationDate) {
      this.expirationDate = expirationDate;
   }
   
}
