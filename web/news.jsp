<%-- 
    Document   : news
    Created on : 05-Aug-2015, 18:37:19
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
   <h1>Newest Release</h1>
   <c:choose>
      <c:when test="${newestProduct == null}">
         <p class="larger_p">There are no products available for purchase right now.</p>
      </c:when>
      <c:otherwise>
         <div class="row">
            <div class="col-md-8">
               <h3><a href="<c:url value='catalog/product/${newestProduct.code}' />">${newestProduct.description}</a></h3>
               <p class="larger_p">${newestProduct.albumInformation}</p>
            </div>
            <div class="col-md-4">
               <img class="img-thumbnail" src="<c:url value='${newestProduct.imageUrl}' />" alt="Cover Image" />
            </div>
         </div>
      </c:otherwise>
   </c:choose>
</div>
