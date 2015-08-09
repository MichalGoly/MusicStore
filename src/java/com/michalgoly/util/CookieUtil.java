package com.michalgoly.util;

import javax.servlet.http.Cookie;

/**
 * This class provide useful methods for working with cookies.
 *
 * @author Michal Goly
 */
public class CookieUtil {

   /**
    * Retrieves cookie value based on its name from the provided list
    *
    * @param cookies The list of cookies
    * @param name Name of the cookie to be found
    * @return Either cookie value, or null if it does not exist or cookies are null
    */
   public static String getCookieValue(Cookie[] cookies, String name) {
      String result = null;
      
      if (cookies != null) {
         for (Cookie c : cookies) {
            if (c.getName().equals(name)) {
               result = c.getValue();
               break;
            }
         }
      }

      return result;
   }

}
