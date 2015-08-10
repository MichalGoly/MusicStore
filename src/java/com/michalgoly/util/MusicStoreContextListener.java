package com.michalgoly.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * This class will execute code within the contextInitialized method
 * whenever the web application is launched. 
 * 
 * @author Michal Goly
 */
public class MusicStoreContextListener implements ServletContextListener {

   @Override
   public void contextInitialized(ServletContextEvent event) {
      ServletContext sContext = event.getServletContext();
      
      // initialize the current year attribute
      GregorianCalendar calendar = new GregorianCalendar();
      int currentYear = calendar.get(Calendar.YEAR);
      sContext.setAttribute("currentYear", currentYear);
   }

   @Override
   public void contextDestroyed(ServletContextEvent event) {
      // not necessary
   }
   
}
