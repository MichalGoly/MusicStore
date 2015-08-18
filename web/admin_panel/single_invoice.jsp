<%-- 
    Document   : single_invoice.jsp
    Created on : 17-Aug-2015, 16:23:36
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
      <div class="col-sm-9 margin-bottom">
         <div class="col-sm-10 col-sm-offset-1">
            <div class="panel panel-default larger_p additional-padding">
               <p><strong>Date:</strong></p>
               <p><fmt:formatDate value="${invoice.invoiceDate}" type="date" dateStyle="short" /></p>

               <p><strong>Ship to:</strong></p>
               <p class="remove-margin"><c:out value='${invoice.customer.firstName} ${invoice.customer.lastName}' /></p>
               <c:if test="${invoice.customer.address.companyName != null}">
                  <p class="remove-margin"><c:out value='${invoice.customer.address.companyName}' /></p>
               </c:if>
               <p class="remove-margin"><c:out value='${invoice.customer.address.address1}' /></p>
               <c:if test="${invoice.customer.address.address2 != null}">
                  <p class="remove-margin"><c:out value='${invoice.customer.address.address2}' /></p>
               </c:if>
               <p class="remove-margin"><c:out value='${invoice.customer.address.city}' /></p>
               <p class="remove-margin"><c:out value='${invoice.customer.address.county}' /></p>
               <p class="remove-margin"><c:out value='${invoice.customer.address.postCode}' /></p>
               <p class="remove-margin"><c:out value='${invoice.customer.address.country}' /></p>

               <table class="table">
                  <colgroup>
                     <col class="col-md-2">
                     <col class="col-md-8">
                     <col class="col-md-2">
                  </colgroup>
                  <thead>
                     <tr>
                        <th>Quantity</th>
                        <th>Description</th>
                        <th>Price</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach var="item" items="${invoice.lineItems}">
                        <tr>
                           <td>${item.quantity}</td>
                           <td>${item.product.description}</td>
                           <td>${item.totalPriceCurrencyFormat}</td>
                        </tr>
                     </c:forEach>
                     <tr>
                        <td><strong>Total:</strong></td>
                        <td>&nbsp;</td>
                        <td>${invoice.invoiceTotalCurrencyFormat}</td>
                     </tr>   
                  </tbody>
               </table>
            </div>
            <c:if test="${!invoice.processed}">
               <form method="post" action="<c:url value='/admin/processInvoice' />">
                  <input type="hidden" name="invoiceNumber" value="<c:out value='${invoice.number}' />" />
                  <input type="submit" value="Process" class="btn btn-success" />
               </form>
            </c:if>
         </div>
      </div>
      <div class="col-sm-3">
         <ul class="nav nav-pills nav-stacked">
            <li class="active"><a href="<c:url value='/admin/processInvoices' />">Process invoices</a></li>
            <li><a href="<c:url value='/admin/displayLogs' />">Downloads Log</a></li>
         </ul>
      </div>
   </div>   
</div>

<jsp:include page="/footer.jsp" />
