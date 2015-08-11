<%-- 
    Document   : register
    Created on : 09-Aug-2015, 12:46:18
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp" />

<div class="container">
   <div class="page-header">
      <h1 class="centre-align">Download Registration</h1>
   </div>
   <p class="centre-align larger_p">
      Before you can download and listen to these sound files, you must 
      register with us by entering your name and email address below.
   </p>      
   <div class="row push_down"> 
      <div class="col-sm-8 col-sm-offset-2">
         <c:if test="${message != null}">
            <div class="alert alert-danger" role="alert">
               <p>${message}</p>
            </div>
         </c:if>     
         <form method="post" action="<c:url value='/catalog/product/${product.code}/listen/register' />">
            <div class="form-group">
               <label for="firstName">First Name:</label>
               <input type="text" name="firstName" id="firstName" class="form-control" required="true" /> 
            </div>
            <div class="form-group">
               <label for="lastName">Last Name:</label>
               <input type="text" name="lastName" id="lastName" class="form-control" required="true" />
            </div>
            <div class="form-group">
               <label for="email">Email:</label>
               <input type="email" name="email" id="email" class="form-control" required="true" />
            </div>
            <div class="form-group">
               <input type="submit" class="btn btn-primary" value="Register" />
            </div>
         </form>
      </div>
   </div>
</div>

<jsp:include page="/footer.jsp" />
