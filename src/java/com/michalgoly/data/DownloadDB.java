package com.michalgoly.data;

import com.michalgoly.business.Download;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * This class can manipulate Download objects in the database
 *
 * @author Michal Goly
 */
public class DownloadDB {

   /**
    * Inserts a Download object into the database
    *
    * @param download The download to be inserted
    */
   public static void insert(Download download) {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      EntityTransaction transaction = em.getTransaction();

      try {
         transaction.begin();
         em.persist(download);
         transaction.commit();
      } catch (Exception e) {
         System.err.println(e);
         transaction.rollback();
      } finally {
         em.close();
      }
   }

   /**
    * @return The list of downloads in the database, null if none exist
    */
   public static List<Download> selectDownloads() {
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      String queryString = "SELECT d FROM Download d";
      Query query = em.createQuery(queryString);

      List<Download> downloads = null;

      try {
         downloads = query.getResultList();
      } catch (Exception e) {
         System.err.println(e);
      } finally {
         em.close();
      }
      
      return downloads;
   }

}
