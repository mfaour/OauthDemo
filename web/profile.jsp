<%-- 
    Document   : profile.jsp
    Created on : Jan 25, 2017, 11:11:28 AM
    Author     : Alienware
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="bean.GoogleOpenIdHelper"%>
<%@page import="java.io.IOException"%>
<%@page import="bean.GoogleAuthHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <link rel="stylesheet" href="css/style.css">
                <title>Profile Page</title>
    </head>
    <body>
        <jsp:include page="default.jsp"></jsp:include> 
            <hr/>  
            <br/>
            <h3>
            <%
                String isLoggedIn = (String) session.getAttribute("LoggedIn");

                if (isLoggedIn == null  || isLoggedIn == "False"  || isLoggedIn == "Error" || isLoggedIn.startsWith("Error")) {
                    response.sendRedirect("login.jsp");
                } else if (request.getParameter("code") == null && request.getParameter("state") == null) {
                    out.print("Hello " + isLoggedIn + ", This is your profile");
                } else {
                    GoogleAuthHelper helper = new GoogleAuthHelper();
                    session.removeAttribute("state");
                    try {
                        Object type = session.getAttribute("AuthType");
                        String strType ="";
                        if (type != null)
                            strType = type.toString();
                        
                        if ( strType == "Google")
                        {
                            try{
                                String jsondata = helper.getUserInfoJson(request.getParameter("code"));
                               // out.print(jsondata);
                               Gson json = new Gson();
                               bean.LoggedInUser user = json.fromJson(jsondata,bean.LoggedInUser.class);
                               
                               
                               String jsonStr = "{\"given_name\":\""+user.given_name+"\",";
                               jsonStr += "\"family_name\":\""+user.family_name+"\",";
                               jsonStr += "\"name\":\""+user.name+"\",";
                               jsonStr += "\"gender\":\""+user.gender+"\",";
                               jsonStr += "\"email\":\""+user.email+"\",";
                               jsonStr += "\"picture\":\""+user.picture+"\"}";
                               
                               out.write("<table class='tbl'>");
                               //out.write("Id: " + user.id);
                                out.write("<tr><td>Json data</td><td>" + jsonStr+"</td></tr>");
                               out.write("<tr><td>First name</td><td>" + user.given_name+"</td></tr>");
                               out.write("<tr><td>Last name</td><td>" + user.family_name+"</td></tr>");
                               out.write("<tr><td>Full name</td><td>" + user.name+"</td></tr>");
                               out.write("<tr><td>Gender</td><td>"  + user.gender+"</td></tr>");
                               out.write("<tr><td>Email</td><td>"  + user.email+"</td></tr>");
                               //out.write("Id:" + user.verified_email+"</td></tr>");
                               //out.write("Id:" + user.link+"</td></tr>");
                               out.write("<tr><td>Picture url</td><td>" + user.picture+"</td></tr></table>");
                            }
                            catch(Exception e)
                            {
                                out.print("Access denied, code already redeemed..");
                                 session.removeAttribute("LoggedIn");
                            }
                        }
                        else
                        {
                        //out.print(helper.getUserInfoJson(request.getParameter("code")));
                        if (request.getParameter("id_token") != null) {
                            out.print(request.getParameter("id_token"));
                        } //
                        else {
                            try{GoogleOpenIdHelper ohelper = new GoogleOpenIdHelper();
                            String openIdUrl = ohelper.getToken(request.getParameter("code"));
                            //response.sendRedirect(openIdUrl);
                            out.print(openIdUrl);
                            }catch(Exception e)
                            {
                                out.print("Access denied, code already redeemed..");
                                session.removeAttribute("LoggedIn");
                            }
                        }
                        }
                        session.removeAttribute("AuthType");
                    } catch (IOException e) {
                        throw(e);
                    }
                }
            %>
        </h3>
    </body>
</html>
