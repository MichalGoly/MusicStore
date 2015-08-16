<%-- 
    Document   : cart
    Created on : 11-Aug-2015, 11:34:56
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp" />

<div class="container">
   <div class="page-header centre-align">
      <h1>Cart</h1>
   </div>
   <c:choose>
      <c:when test="${emptyMessage != null}">
         <h3>${emptyMessage}</h3>
         <a href="<c:url value='/catalog' />" class="btn btn-primary">Continue shopping</a>
      </c:when>
      <c:otherwise>
         <div class="row">
            <div class="col-sm-4">
               <p class="larger_p">
                  <strong>To change the quantity for an item,</strong> enter the new
                  quantity and click on the Update button.
               </p>
               <p class="larger_p">
                  <strong>To remove an item,</strong> click on the remove button.
               </p>
               <a href="<c:url value='/catalog' />" class="btn btn-default float-left">Continue shopping</a>
               <form method="post" action="<c:url value='/order/checkCustomer'/>">
                  <input type="submit" class="btn btn-primary" value="Checkout" />
               </form>
            </div>
            <div class="col-sm-8">
               <div class="table-responsive">
                  <table class="table">
                     <colgroup>
                        <col class="col-md-1">
                        <col class="col-md-8">
                        <col class="col-md-1">
                        <col class="col-md-1">
                        <col class="col-md-1">
                     </colgroup>
                     <thead>
                        <tr>
                           <th>Quantity</th>
                           <th>Description</th>
                           <th>Price</th>
                           <th>Amount</th>
                           <th>&nbsp;</th>
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach var="item" items="${cart.items}">
                           <tr>
                              <td>
                                 <form method="post" action="<c:url value='/order/updateItem'/>">
                                    <input type="hidden" name="productCode" value="<c:out value='${item.product.code}' />" />
                                    <input type="text" name="quantity" value="<c:out value='${item.quantity}' />" />
                                    <input type="submit" class="btn btn-default" value="Update" />
                                 </form>
                              </td>
                              <td>${item.product.description}</td>
                              <td>${item.product.priceCurrencyFormat}</td>
                              <td>${item.totalPriceCurrencyFormat}</td>
                              <td>
                                 <form method="post" action="<c:url value='/order/removeItem'/>">
                                    <input type="hidden" name="productCode" value="<c:out value='${item.product.code}' />" />
                                    <input type="submit" class="btn btn-danger" value="Delete" />
                                 </form>
                              </td>
                           </tr>
                        </c:forEach>
                        <tr>
                           <td><strong>Total:</strong></td>
                           <td>&nbsp;</td>
                           <td>&nbsp;</td>
                           <td>${cart.totalPriceCurrencyFormat}</td>
                           <td>&nbsp;</td>
                        </tr>     
                     </tbody>
                  </table>
               </div>
            </div>
         </div>
      </c:otherwise>
   </c:choose>
</div>

<jsp:include page="/footer.jsp" />
