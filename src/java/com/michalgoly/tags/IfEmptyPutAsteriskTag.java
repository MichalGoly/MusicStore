package com.michalgoly.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * This class represents a custom JSP tag which can be used within a jsp file. 
 * Typically you can use it in a form, by specifying the field attribute. the field 
 * should correspond to one of the text inputs in the form, and the tag will print 
 * an asterisk if the field is null or is empty. Otherwise it will print nothing. 
 * 
 * Asterisk has a class named 'asterisk', which can be used to manipulate for instance
 * its style or access it from the JavaScript. 
 * 
 * @author Michal Goly
 */
public class IfEmptyPutAsteriskTag extends TagSupport {

   private String field;

   public void setField(String field) {
      this.field = field;
   }

   @Override
   public int doStartTag() throws JspException {
      try {
         JspWriter out = pageContext.getOut();
         if (field == null || field.isEmpty()) {
            out.print("<span class='asterisk'> *</span>");
         }
      } catch (IOException e) {
         System.err.println(e);
      }

      return SKIP_BODY;
   }

}
