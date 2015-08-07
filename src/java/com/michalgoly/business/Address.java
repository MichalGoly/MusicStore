package com.michalgoly.business;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This Entity represents customer's address.
 *
 * @author Michal Goly
 */
@Entity
public class Address implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long addressId;

   private String companyName;
   private String address1;
   private String address2;
   private String city;
   private String county;
   private String postCode;
   private String country;
   
   public Address() {}
   
   public Long getAddressId() {
      return addressId;
   }

   public String getCompanyName() {
      return companyName;
   }

   public String getAddress1() {
      return address1;
   }

   public String getAddress2() {
      return address2;
   }

   public String getCity() {
      return city;
   }

   public String getCounty() {
      return county;
   }

   public String getPostCode() {
      return postCode;
   }

   public String getCountry() {
      return country;
   }

   public void setAddressId(Long addressId) {
      this.addressId = addressId;
   }

   public void setCompanyName(String companyName) {
      this.companyName = companyName;
   }

   public void setAddress1(String address1) {
      this.address1 = address1;
   }

   public void setAddress2(String address2) {
      this.address2 = address2;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public void setCounty(String county) {
      this.county = county;
   }

   public void setPostCode(String postCode) {
      this.postCode = postCode;
   }

   public void setCountry(String country) {
      this.country = country;
   }

}
