<%-- 
    Document   : footer
    Created on : 05-Aug-2015, 15:54:30
    Author     : Michal Goly
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer>
   <div class="container">
      <div class="row">
         <div class="col-sm-8 col-sm-offset-2 centre-align push_down">
            <p>This website uses persistent cookies which you can delete 
               <a href="<c:url value='/cookies/delete' />">here</a>, to test the web app functionality.</p>
            <p>Code released under the MIT license. Copyright (c) ${currentYear} 
               <a href="http://michalgoly.com">Michal Goly</a>.</p>
            <p>
               <a href="https://github.com/MichalGoly/MusicStore"><span class="fa fa-github fa-2x"></span></a>
            </p>
         </div>
      </div>
   </div>	
</footer>
</body>
</html>
