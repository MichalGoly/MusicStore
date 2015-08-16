<%-- 
    Document   : listen.jsp
    Created on : 09-Aug-2015, 11:11:57
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp" />

<div class="container push_down">
   <div class="row">
      <div class="col-sm-8">
         <h1>${product.artistName}</h1>
         <h3>${product.albumName}</h3>
         <p class="larger_p">You can listen to the selected tracks by clicking on the play icons below.</p>
      </div>
      <div class="col-sm-4">
         <img class="img-thumbnail" src="<c:url value='${product.imageUrl}' />" alt="Cover Image" />
      </div>
   </div>
   <div class="row push_down">
      <div class="col-sm-8">
         <ul class="list-group push_down">
            <c:forEach var="title" items="${product.titlesList}" varStatus="i" >
               <li class="list-group-item">
                  <c:choose>
                     <c:when test="${i.index == 1 || i.index == 5}">
                        ${i.index + 1}. ${title} 
                        <span class="badge">
                           <a href="<c:url value='/sound/${product.code}/${i.index + 1}.mp3' />">
                              <span class="glyphicon glyphicon-play"></span>
                           </a>
                        </span>
                     </c:when>
                     <c:otherwise>
                        ${i.index + 1}. ${title}
                     </c:otherwise>
                  </c:choose>
               </li>
            </c:forEach>
         </ul>
      </div>
      <div class="col-sm-4">
         <p class="price">${product.priceCurrencyFormat}</p>
         <form method="post" action="<c:url value='/order/addItem'/>">
            <input type="hidden" name="productCode" value="<c:out value='${product.code}' />" />
            <input type="submit" class="btn btn-primary" value="Add to Cart" />
         </form>         
      </div>
   </div>      
</div>       

<jsp:include page="/footer.jsp" />