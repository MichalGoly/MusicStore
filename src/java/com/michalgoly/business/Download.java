package com.michalgoly.business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Michal Goly
 */
@Entity
public class Download implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long downloadId;

   @ManyToOne(fetch = FetchType.EAGER)
   private Customer customer;

   @Temporal(TemporalType.DATE)
   private Date downloadDate;

   private String downloadCode;

   public Download() {
      downloadDate = new Date();
   }

   public Long getDownloadId() {
      return downloadId;
   }

   public Customer getCustomer() {
      return customer;
   }

   public Date getDownloadDate() {
      return downloadDate;
   }

   public String getDownloadCode() {
      return downloadCode;
   }

   public void setDownloadId(Long downloadId) {
      this.downloadId = downloadId;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public void setDownloadDate(Date downloadDate) {
      this.downloadDate = downloadDate;
   }

   public void setDownloadCode(String downloadCode) {
      this.downloadCode = downloadCode;
   }
}
