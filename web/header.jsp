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
      
      <link href="<c:url value='/includes/css/style.css' />" rel="stylesheet" />
      
      <!-- Font awesome -->
      <link href="<c:url value='/includes/css/font-awesome.min.css' />" rel="stylesheet" />
      
      <!-- jQuery script which makes appropriate navbar tab active -->
      <script>
         var j$ = jQuery.noConflict();
         j$(document).ready(function() {
            j$(".nav").find(".active").removeClass("active");
            var url = j$(location).attr('href');
            if (url.indexOf("catalog") >= 0) {
               j$("#catalog").addClass("active");
            } else if (url.indexOf("subscribe") >= 0) {
               j$("#subscribe").addClass("active");
            } else if (url.indexOf("contact") >= 0) {
               j$("#contact").addClass("active");
            } else if (url.indexOf("order") >= 0) {
               j$("#order").addClass("active");
            } else {
               j$("#home").addClass("active");
            }
         });
      </script>
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
               <a class="navbar-brand" href="<c:url value='/' />">Music Store</a>
            </div>
            <div class="collapse navbar-collapse">
               <ul class="nav navbar-nav">
                  <li id="home"><a href="<c:url value='/' />">Home</a></li>
                  <li id="catalog"><a href="<c:url value='/catalog' />">Catalog</a></li>
                  <li id="subscribe"><a href="<c:url value='/subscribe' />">Subscribe</a></li> 
                  <li id="contact"><a href="<c:url value='/contact' />">Contact</a></li> 
               </ul>
               <ul class="nav navbar-nav navbar-right">
                  <li id="order"><a href="<c:url value='/order/showCart' />"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
               </ul>
            </div>
         </div>
      </div>
