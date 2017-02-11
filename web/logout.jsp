<%-- 
    Document   : logout
    Created on : Jan 25, 2017, 11:06:11 AM
    Author     : Alienware
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" href="css/style.css">
                <title>Logout Page</title>
    </head>
    <body>
        <jsp:include page="default.jsp"></jsp:include> 
         <hr/>  
         <br/>
         <h3>
              <%
                session.setAttribute("LoggedIn", "False");
                out.print("You are logged out");
            %>  
         </h3>
    </body>
</html>
