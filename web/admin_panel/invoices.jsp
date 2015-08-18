<%-- 
    Document   : invoices.jsp
    Created on : 17-Aug-2015, 16:13:38
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
            <c:when test="${invoices == null}">
               <h3 class="centre-align">There are no invoices in the database.</h3>
            </c:when>
            <c:otherwise>
               <p class="larger_p">You can view the processed and unprocessed invoices below.</p>
               <table class="table">
                  <thead>
                     <tr>
                        <th>Invoice Number</th>
                        <th>Customer</th>
                        <th>Invoice Date</th>
                        <th>Processed</th>
                        <th>&nbsp;</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach var="invoice" items="${invoices}">
                        <tr>
                           <td>${invoice.number}</td>
                           <td>${invoice.customer.firstName} ${invoice.customer.lastName}</td>
                           <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${invoice.invoiceDate}" /></td>
                           <td>
                              <c:choose>
                                 <c:when test="${invoice.processed}" >
                                    <span class="fa fa-check green"></span>
                                 </c:when>
                                 <c:otherwise>
                                    <span class="fa fa-times red"></span>
                                 </c:otherwise>
                              </c:choose>
                           </td>
                           <td>
                              <a href="<c:url value='/admin/${invoice.number}/displayInvoice' />" class="btn btn-default">View</a>
                           </td>
                        </tr>
                     </c:forEach>
                  </tbody>
               </table>
            </c:otherwise>
         </c:choose>
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