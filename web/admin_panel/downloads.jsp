<%-- 
    Document   : downloads
    Created on : 16-Aug-2015, 21:58:57
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/header.jsp" />

<div class="container">
   <div class="page-header centre-align">
      <h1>Admin Panel</h1>
   </div>
   <div class="row">
      <div class="col-sm-9">
         <c:choose>
            <c:when test="${downloads == null}">
               <h3 class="centre-align">There are no download logs available.</h3>
            </c:when>
            <c:otherwise>
               <p class="larger_p">You can view the download logs below.</p>
               <table class="table">
                  <thead>
                     <tr>
                        <th>Date</th>
                        <th>Customer</th>
                        <th>Product</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach var="download" items="${downloads}">
                        <tr>
                           <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${download.downloadDate}" /></td>
                           <td>${download.customer.firstName} ${download.customer.lastName}</td>
                           <td>${download.product.description}</td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </c:otherwise>
         </c:choose>
      </div>
      <div class="col-sm-3">
         <ul class="nav nav-pills nav-stacked">
            <li><a href="<c:url value='/admin/processInvoices' />">Process invoices</a></li>
            <li class="active"><a href="<c:url value='/admin/displayLogs' />">Downloads Log</a></li>
         </ul>
      </div>
   </div>   
</div>

<jsp:include page="/footer.jsp" />
