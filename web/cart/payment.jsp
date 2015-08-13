<%-- 
    Document   : payment
    Created on : 13-Aug-2015, 20:20:17
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp" />

<div class="container">
   <div class="page-header centre-align">
      <h1>Enter your credit card details</h1>
   </div>
   <div class="row">
      <div class="col-sm-8 col-sm-offset-2">
         <form method="post" action="<c:url value='/order/complete' />">
            <div class="form-group">
               <label for="cardType">Card Type:</label>
               <select name="cardType" id="cardType" class="form-control">
                  <option value="visa">Visa</option>
                  <option value="masterCard">MasterCard</option>
               </select>
            </div>
            <div class="form-group">
               <label for="cardNumber">Card Number:</label>
               <input type="text" name="cardNumber" id="cardNumber" class="form-control" maxlength="16" required="true" />
            </div>
            <div class="form-group">
               <label for="expirationMonth">Expiration Date MM/YYYY:</label>
               <select name="expirationMonth" id="expirationMonth" class="form-control float-left">
                  <option value="01">01</option>
                  <option value="02">02</option>
                  <option value="03">03</option>
                  <option value="04">04</option>
                  <option value="05">05</option>
                  <option value="06">06</option>
                  <option value="07">07</option>
                  <option value="08">08</option>
                  <option value="09">09</option>
                  <option value="10">10</option>
                  <option value="11">11</option>
                  <option value="12">12</option>
               </select>
               /
               <select name="expirationYear" class="form"
            </div>
            <div class="form-group">
               <input type="submit" class="btn btn-primary" value="Submit Order" />
            </div>   
         </form>
      </div>
   </div>
</div>

<jsp:include page="/footer.jsp" />

