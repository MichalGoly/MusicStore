package com.michalgoly.data;

import com.michalgoly.business.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * This class can manipulate information about products in the database.
 * 
 * @author Michal Goly
 */
public class ProductDB {
   
   /**
    * Inserts a new product to the database
    * @param product The product to be added
    */
   public static void insert(Product product) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();
      
      try {
         transaction.begin();
         em.persist(product);
         transaction.commit();
      } catch (Exception e) {
         System.err.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }
   
   /**
    * @return The list of all products in the database, or null if there are none
    */
   public static List<Product> selectProducts() {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT p FROM Product p";
      Query query = em.createQuery(queryString);
      
      List<Product> products = null;
      
      try {
         products = query.getResultList();
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }
      
      return products;
   }
   
}
