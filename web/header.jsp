<%-- 
    Document   : header.jsp
    Created on : 05-Aug-2015, 15:53:22
    Author     : Michal Goly
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-GB">
   <head>
      <title>Music Store</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <meta name="author" content="Michal Goly" />

      <!-- jQuery -->
      <script src="<c:url value='/includes/js/jquery-2.1.4.min.js' />"></script>

      <!-- Bootstrap -->
      <link href="<c:url value='/includes/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" />
      <script src="<c:url value='/includes/bootstrap/js/bootstrap.min.js' />"></script>
   </head>
   <body>
      <div class="navbar navbar-default">
         <div class="container">
            <div class="navbar-header">
               <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="#">Music Store</a>
            </div>
            <div>
               <ul class="nav navbar-nav">
                  <li><a href="#">Home</a></li>
                  <li><a href="#">Catalog</a></li>
                  <li<a href="#">Subscribe</a></li> 
                  <li<a href="#">Contact</a></li> 
               </ul>
            </div>
         </div>
      </div>
   </body>
</html>
