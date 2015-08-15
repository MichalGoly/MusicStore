<%-- 
    Document   : thanks
    Created on : 15-Aug-2015, 15:45:11
    Author     : Michal Goly
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp" />

<div class="container">
   <div class="page-header centre-align">
      <h1>Thank you, <c:out value='${subscriber.firstName}' /></h1>
   </div>
   <p class="larger_p centre-align">
      You have successfully subscribed to our newsletter. You can find
      the information you have entered below.
   </p>
   <div class="row">
      <div class="col-sm-6 col-sm-offset-3">
         <div class="panel panel-default larger_p additional-padding">
            <p><strong>First Name:</strong></p>
            <p><c:out value='${subscriber.firstName}' /></p>
            <p><strong>Last Name:</strong></p>
            <p><c:out value='${subscriber.lastName}' /></p>
            <p><strong>Email:</strong></p>
            <p><c:out value='${subscriber.email}' /></p>
         </div>
      </div>
   </div>
</div>

<jsp:include page="/footer.jsp" />
