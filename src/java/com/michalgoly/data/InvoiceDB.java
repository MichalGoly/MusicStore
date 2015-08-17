package com.michalgoly.data;

import com.michalgoly.business.Invoice;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * This class can manipulate Invoice objects in the database
 *
 * @author Michal Goly
 */
public class InvoiceDB {

   /**
    * Inserts a new invoice to the database
    *
    * @param invoice The invoice to be added
    */
   public static void insert(Invoice invoice) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.persist(invoice);
         transaction.commit();
      } catch (Exception e) {
         System.err.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }

   /**
    * Updates the invoice object in the database
    *
    * @param invoice The object to be updated
    */
   public static void update(Invoice invoice) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.merge(invoice);
         transaction.commit();
      } catch (Exception e) {
         System.out.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }

   /**
    * @return A list of all Invoices in the db, null if there are none
    */
   public static List<Invoice> selectInvoices() {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT i FROM Invoice i";
      Query query = em.createQuery(queryString);

      List<Invoice> invoices = null;

      try {
         invoices = query.getResultList();
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }

      return invoices;
   }

   /**
    * Selects a single Invoice object from the database based on its primary key.
    *
    * @param invoiceNumber The primary key, number of the invoice
    * @return Either the Invoice with specified PK, or null if it does not exist
    */
   public static Invoice select(Long invoiceNumber) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      Invoice invoice = null;

      try {
         invoice = em.find(Invoice.class, invoiceNumber);
      } finally {
         em.close();
      }

      return invoice;
   }
}
