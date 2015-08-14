package com.michalgoly.data;

import com.michalgoly.business.Invoice;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
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
}
