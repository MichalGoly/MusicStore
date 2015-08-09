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
         <p class="album_info">${product.albumInformation}</p>
      </div>
      <div class="col-sm-4">
         <img class="img-thumbnail" src="<c:url value='${product.imageUrl}' />" alt="Cover Image" />
      </div>
   </div>
   <div class="row push_down">
      <div class="col-sm-8">
      </div>
      <div class="col-sm-4">
         <p class="price">${product.priceCurrencyFormat}</p>
         <div class="btn-group">
            <a href="#" class="btn btn-primary">Add to Cart</a>
            <a href="<c:url value='/catalog/product/${product.code}/listen' />" class="btn btn-default">Listen to Samples</a>
         </div>
      </div>
   </div>
</div>

<jsp:include page="/footer.jsp" />