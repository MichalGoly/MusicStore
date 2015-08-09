package com.michalgoly.data;

import com.michalgoly.business.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 * This class can manipulate customer objects in the database
 *
 * @author Michal Goly
 */
public class CustomerDB {

   /**
    * Retrieves a single customer from the database using provided email address.
    *
    * @param email The email address of a customer
    * @return Either appropriate Customer object, or null if he doesn't exist
    */
   public static Customer selectByEmail(String email) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT c FROM Customer c "
              + "WHERE c.email = :email";
      TypedQuery<Customer> query = em.createQuery(queryString, Customer.class);
      query.setParameter("email", email);

      Customer customer = null;
      try {
         customer = query.getSingleResult();
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }

      return customer;
   }

   /**
    * @param customer The customer to be inserted into a database
    */
   public static void insert(Customer customer) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.persist(customer);
         transaction.commit();
      } catch (Exception e) {
         System.out.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }
   
   /**
    * @param customer The customer to be updated
    */
   public static void update(Customer customer) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.merge(customer);
         transaction.commit();
      } catch (Exception e) {
         System.out.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }
   
   /**
    * Checks if user with specified email exists within the database
    * 
    * @param email The email address to be checked
    * @return True if a customer with given email exists, false otherwise
    */
   public static boolean emailExists(String email) {
      return selectByEmail(email) != null;
   }
   
}
