package com.michalgoly.data;

import com.michalgoly.business.Subscriber;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 * This class manipulates subscriber objects in the database.
 *
 * @author Michal Goly
 */
public class SubscriberDB {

   /**
    * Retrieves a single subscriber from the database using provided email address.
    *
    * @param email The email address of a subscriber
    * @return Either appropriate Subscriber object, or null if he doesn't exist
    */
   public static Subscriber selectByEmail(String email) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT s FROM Subscriber s "
              + "WHERE s.email = :email";
      TypedQuery<Subscriber> query = em.createQuery(queryString, Subscriber.class);
      query.setParameter("email", email);

      Subscriber subscriber = null;
      try {
         subscriber = query.getSingleResult();
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }

      return subscriber;
   }

   /**
    * @param subscriber The subscriber to be inserted into a database
    */
   public static void insert(Subscriber subscriber) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.persist(subscriber);
         transaction.commit();
      } catch (Exception e) {
         System.out.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }

   /**
    * @param subscriber The subscriber to be updated
    */
   public static void update(Subscriber subscriber) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.merge(subscriber);
         transaction.commit();
      } catch (Exception e) {
         System.out.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }

   /**
    * Checks if subscriber with specified email exists within the database
    *
    * @param email The email address to be checked
    * @return True if a subscriber with given email exists, false otherwise
    */
   public static boolean emailExists(String email) {
      return selectByEmail(email) != null;
   }
}
