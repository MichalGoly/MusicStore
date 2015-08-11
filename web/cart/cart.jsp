<%-- 
    Document   : cart
    Created on : 11-Aug-2015, 11:34:56
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp" />

<div class="container">
   <div class="page-header">
      <h1>Cart</h1>
   </div>
   <c:choose>
      <c:when test="${emptyMessage != null}">
         <h3>${emptyMessage}</h3>
         <a href="<c:url value='/catalog' />" class="btn btn-primary">Continue shopping</a>
      </c:when>
      <c:otherwise>
         <!-- TODO -->
      </c:otherwise>
   </c:choose>
</div>

<jsp:include page="/footer.jsp" />
