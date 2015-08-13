<%-- 
    Document   : new_customer
    Created on : 11-Aug-2015, 18:16:52
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="goly" uri="/WEB-INF/customTags.tld" %>

<jsp:include page="/header.jsp" />

<div class="container">
   <div class="page-header centre-align">
      <h1>Checkout</h1>
   </div>
   <p class="larger_p centre-align">Please enter your name and contact information</p>
   <div class="row">
      <div class="col-sm-8 col-sm-offset-2">
         <c:if test="${message != null}">
            <div class="alert alert-danger" role="alert">
               <p>${message}</p>
            </div>
         </c:if>         
         <form method="post" action="<c:url value='/order/processCustomer' />">
            <div class="form-group">
               <label for="firstName">First Name:</label>
               <goly:ifEmptyPutAsterisk field="${customer.firstName}" />
               <input type="text" name="firstName" id="firstName" class="form-control" 
                      value="<c:out value='${customer.firstName}' />" required="true" />              
            </div>
            <div class="form-group">
               <label for="lastName">Last Name:</label>
               <goly:ifEmptyPutAsterisk field="${customer.lastName}" />
               <input type="text" name="lastName" id="lastName" class="form-control" 
                      value="<c:out value='${customer.lastName}' />" required="true" />
            </div>
            <div class="form-group">
               <label for="email">Email:</label>
               <goly:ifEmptyPutAsterisk field="${customer.email}" />
               <input type="email" name="email" id="email" class="form-control" 
                      value="<c:out value='${customer.email}' />" required="true"/>
            </div>
            <div class="form-group">
               <label for="companyName">Company:</label>
               <input type="text" name="companyName" id="companyName" 
                      value="<c:out value='${customer.address.companyName}' />" class="form-control" />
            </div>
            <div class="form-group">
               <label for="address1">Address1:</label>
               <goly:ifEmptyPutAsterisk field="${customer.address.address1}" />
               <input type="text" name="address1" id="address1" class="form-control" 
                      value="<c:out value='${customer.address.address1}' />" required="true" />
            </div>
            <div class="form-group">
               <label for="address2">Address2:</label>
               <input type="text" name="address2" id="address2" class="form-control" 
                      value="<c:out value='${customer.address.address2}' />" />
            </div>
            <div class="form-group">
               <label for="city">City:</label>
               <goly:ifEmptyPutAsterisk field="${customer.address.city}" />
               <input type="text" name="city" id="city" class="form-control" 
                      value="<c:out value='${customer.address.city}' />" required="true" />
            </div>
            <div class="form-group">
               <label for="county">County:</label>
               <goly:ifEmptyPutAsterisk field="${customer.address.county}" />
               <input type="text" name="county" id="county" class="form-control" 
                      value="<c:out value='${customer.address.county}' />" required="true" />
            </div>
            <div class="form-group">
               <label for="postCode">Post Code:</label>
               <goly:ifEmptyPutAsterisk field="${customer.address.postCode}" />
               <input type="text" name="postCode" id="postCode" class="form-control" 
                      value="<c:out value='${customer.address.postCode}' />" required="true" />
            </div>
            <div class="form-group">
               <label for="country">Country:</label>
               <goly:ifEmptyPutAsterisk field="${customer.address.country}" />
               <input type="text" name="country" id="country" class="form-control" 
                      value="<c:out value='${customer.address.country}' />" required="true" />
            </div>
            <div class="form-group">
               <input type="submit" class="btn btn-primary" value="Continue" />
            </div>
         </form>
      </div>
   </div>
</div>

<jsp:include page="/footer.jsp" />

