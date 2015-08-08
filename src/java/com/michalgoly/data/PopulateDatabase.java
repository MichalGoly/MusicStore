package com.michalgoly.data;

import com.michalgoly.business.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * You can run this class to fill the database with data
 *
 * @author Michal Goly
 */
public class PopulateDatabase {

   public static void main(String[] args) {
      List<Product> products = createProducts();
      EntityManager em = DBUtil.getEmFactory().createEntityManager();
      
      products.stream().forEach((p) -> {
         ProductDB.insert(p);
      });
   }

   private static List<Product> createProducts() {
      List<Product> products = new ArrayList<>();

      Product p1 = new Product();
      p1.setCode("8601");
      p1.setDescription("86 (the band) - True Life Songs and Pictures");
      p1.setPrice(new BigDecimal(32.99));

      Product p2 = new Product();
      p2.setCode("pf01");
      p2.setDescription("Paddlefoot - The first CD");
      p2.setPrice(new BigDecimal(12.95));

      Product p3 = new Product();
      p3.setCode("pf02");
      p3.setDescription("Paddlefoot - The second CD");
      p3.setPrice(new BigDecimal(11.95));

      Product p4 = new Product();
      p4.setCode("jr01");
      p4.setDescription("Joe Rut - Genuine Wood Grained Finish");
      p4.setPrice(new BigDecimal(14.50));
      
      products.add(p1);
      products.add(p2);
      products.add(p3);
      products.add(p4);
      
      return products;
   }

}
