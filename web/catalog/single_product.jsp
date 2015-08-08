<%-- 
    Document   : single_product
    Created on : 08-Aug-2015, 16:34:37
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp" />

<div class="container push_down">
   <div class="row">
      <div class="col-sm-8">
         <h1>${product.artistName}</h1>
         <h3>${product.albumName}</h3>
      </div>
      <div class="col-sm-4">
         <img src="<c:url value='${product.imageUrl}' />" alt="Cover Image" />
      </div>
   </div>
   <div class="row">
      <p>Description and song titles will go here</p>
   </div>
</div>

<jsp:include page="/footer.jsp" />