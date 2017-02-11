<%-- 
    Document   : openId
    Created on : Jan 27, 2017, 9:50:59 PM
    Author     : Alienware
--%>

<%@page import="bean.GoogleOpenIdHelper"%>
<%@page import="bean.GoogleAuthHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" href="css/style.css">
                <title>OpenId Page</title>
    </head>
    <body>
       <jsp:include page="default.jsp"></jsp:include> 
            <hr/>  
            <br/>
            <h3>
            <%
                String isLoggedIn = (String) session.getAttribute("LoggedIn");

                if (isLoggedIn == null || isLoggedIn == "Error" || isLoggedIn.startsWith("Error")) {
                    response.sendRedirect("login.jsp");
                } else if (request.getParameter("code") == null && request.getParameter("state") == null) {
                    out.print("Hello " + isLoggedIn + ", This is your profile");
                } else {
                    GoogleAuthHelper helper = new GoogleAuthHelper();
                    session.removeAttribute("state");
                    try {
                        if (request.getParameter("id_token") != null) {
                            out.print(request.getParameter("id_token"));
                        }
                        //out.print(helper.getUserInfoJson(request.getParameter("code")));
                      

                    } catch (IOException e) {
                    }
                }
            %>
        </h3>
    </body>
</html>
