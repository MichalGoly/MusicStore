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
      p1.setAlbumInformation("The debut album from 86 (the band), True Life "
              + "Songs and Pictures rocks and twangs in equal measure. Filled "
              + "with banjo, one-string bass, fiddle, and 3-part harmonies, "
              + "this semi-rock, semi-country, semi-bluegrass album covers a "
              + "lot of ground. \"How to Get There\" is a rambling epic that "
              + "unleashes a rapid-fire lyrical barrage while \"Don't Close Your "
              + "Eyes\" and \"Morning Sun\" are more melancholy and bittersweet.");
      p1.setCsAlbumTitles("How to Get There,You Are a Star,Don't Make No Difference,"
              + "Unidentified Fiddling Object,Beat Up Old Car,Wildflowers,"
              + "What You Whistle When You Walk Home,Three Sheets to the Wind,"
              + "Singin' Drunk,Don't Close Your Eyes,Morning Sun");
      
      Product p2 = new Product();
      p2.setCode("pf01");
      p2.setDescription("Paddlefoot - The first CD");
      p2.setPrice(new BigDecimal(12.95));
      p2.setAlbumInformation("This 68-minute opus from San Francisco's Paddlefoot " +
              "doesn't pull any punches. Songs like \"64 Corvair, Part " +
              "2\", \"Whiskey Before Breakfast\", and \"The Murphy-Ryan " +
              "Polkas\" mix traditional American and Irish fiddle tunes " +
              "with indie rock. The result is somewhere between The Pogues, " +
              "Camper Van Beethoven, and Uncle Tupelo. The sincerity and " +
              "quirkiness of other songs like \"When I Was Nine\" and \"Tiny " +
              "House\" are more reminiscent of Jonathan Richman.");
      p2.setCsAlbumTitles("Pete and Jimmy,Whiskey Before Breakfast,Fishing Rod," + 
              "The Murphy-Ryan Polkas,John Henry's Blues,64 Corvair, Part 2," + 
              "Racoons Like Moonshine Too,Shelf Life,Hey Chris," + 
              "Rock and Roll Scene,Tiny House,Strum Along Slow," + 
              "She's Moving Back Home,When I Was Nine,Kangaroo's Paw," + 
              "Sound of the Fiddle,Amatxi Smiled,Yeah,Dashboard Waltz");
      
      Product p3 = new Product();
      p3.setCode("pf02");
      p3.setDescription("Paddlefoot - The second CD");
      p3.setPrice(new BigDecimal(11.95));
      p3.setAlbumInformation("The second CD from San Francisco's Paddlefoot " +
              "finds the band maturing as it roams through much of " +
              "the same musical terrain as the previous CD. While this album " +
              "occasionally rocks, it also has its introspective and bittersweet " +
              "moments. \"Neon Lights\" is a romantic tribute to neon " +
              "lights, \"Twist Away\" is a heartfelt song of longing and loss, " +
              "and \"Let the Possums Play Possum\" is a instrumental romp that's " +
              "driven by dual fiddles. If you liked the first CD, you'll like " +
              "this one too.");
      p3.setCsAlbumTitles("Neon Lights,Just About Midnight,Tank Hill,Let the " + 
              "Possums Play Possum,Find Me,Take a Picture,Billy Banjo,Most of " + 
              "the Time,Armenian Wedding,West Portal,Twist Away,Kern County " + 
              "Waltz,Distance,Sunshine on the Trees");
      
      Product p4 = new Product();
      p4.setCode("jr01");
      p4.setDescription("Joe Rut - Genuine Wood Grained Finish");
      p4.setPrice(new BigDecimal(14.50));
      p4.setAlbumInformation("The debut album from Joe Rut rambles from " + 
              "Byrds-esque folk pop of \"Filter\" to the country sounds " + 
              "of \"Find My Way Marie\" to psychedelic Brit-pop tunes " + 
              "like \"A Place In All This.\" This well-crafted album is " + 
              "unique and cohesive, revealing its many layers on repeated listens.");
      p4.setCsAlbumTitles("Filter,Find My Way Marie,Hole,1400 Years,So Long " + 
              "Lazy Ray,A Tuna Is a Damn Big Fish,El Dorado,Dream of You,This " + 
              "Sea Is Full of Monsters,A Place in All This,GTTSWMD,AM Land,Whole " + 
              "Month of Sundays,Penny From a Poor Man");
      
      products.add(p1);
      products.add(p2);
      products.add(p3);
      products.add(p4);
      
      return products;
   }
   
}
