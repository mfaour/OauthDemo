<%-- 
    Document   : loginprocess
    Created on : Jan 24, 2017, 10:15:17 AM
    Author     : Alienware
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" href="css/style.css">
                <title>Processing Page</title>
    </head>
    <body>
        <%@page import="bean.LoginDao"%>  
        <jsp:useBean id="obj" class="bean.LoginBean"/>  

        <jsp:setProperty property="*" name="obj"/>  

         <jsp:include page="default.jsp"></jsp:include> 
         <hr/>  
         <br/>
         <h3>
              <%
            String status = LoginDao.validate(obj, request,response);
            
            if (!status.startsWith("Error")) {
                session.setAttribute("LoggedIn", status);
                 out.println("Hello "+ status+", you are successfully logged In");
            }
            else 
                out.println(status);
           
        %>  
         </h3>
       
       
        
    </body>
</html>
