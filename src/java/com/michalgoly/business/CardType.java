package com.michalgoly.business;

/**
 * Simple Enum which represents accepted credit card types.
 *
 * @author Michal Goly
 */
public enum CardType {

   VISA("visa"), MASTER_CARD("masterCard");
   
   private final String name;
   
   private CardType(String name) {
      this.name = name;
   }
   
   /**
    * Static method which creates and returns an Enum based on the provided String
    * value.
    *
    * @param stringCardType A String representation of the required Enum
    * @return Either the required Enum or a null for an invalid input
    */
   public static CardType fromString(String stringCardType) {
      CardType cardType = null;
      for (CardType ct : CardType.values()) {
         if (stringCardType.equals(ct.name)) {
            cardType = ct;
            break;
         }
      }
      return cardType;
   }
}
