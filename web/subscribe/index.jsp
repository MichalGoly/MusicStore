<%-- 
    Document   : index
    Created on : 15-Aug-2015, 15:16:28
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp" />

<div class="container">
   <div class="page-header centre-align">
      <h1>Subscribe to our newsletter!</h1>
   </div>
   <p class="larger_p centre-align">
      If you would like to receive announcements about new releases and 
      special offers, please fill the form below.
   </p>
   <div class="row">
      <div class="col-sm-8 col-sm-offset-2">
         <c:if test="${message != null}">
            <div class="alert alert-info" role="alert">
               <p>${message}</p>
            </div>
         </c:if>
         <form method="post" action="<c:url value='/newsletter/subscribe' />">
            <div class="form-group">
               <label for="firstName">First Name:</label>
               <input type="text" name="firstName" id="firstName" class="form-control" 
                      value="<c:out value='${subscriber.firstName}' />" required="true" />
            </div>
            <div class="form-group">
               <label for="lastName">Last Name:</label>
               <input type="text" name="lastName" id="lastName" class="form-control" 
                      value="<c:out value='${subscriber.lastName}' />" required="true" />
            </div>
            <div class="form-group">
               <label for="email">Email:</label>
               <input type="email" name="email" id="email" class="form-control" 
                      value="<c:out value='${subscriber.email}' />" required="true" />
            </div>            
            <div class="form-group">
               <input type="submit" value="Join" class="btn btn-primary" />
            </div>
         </form>        
      </div>
   </div>
</div>

<jsp:include page="/footer.jsp" />

