<%-- 
    Document   : login
    Created on : Jan 24, 2017, 10:08:45 AM
    Author     : Alienware
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Login Page</title>
    </head>
    <body>
        <%@ include file="default.jsp" %>  
        <hr/>  

        <h3>Login Form</h3>  

        <br/> 
        <div class="login">
            
        <form action="loginprocess.jsp" method="post">  
            
            
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" placeholder="User name"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" placeholder="Password"/></td>
                </tr>
                <tr>
                    <td>Authentication Type:</td>
                    <td><select name="authenticationType" >
                            <option value="Custom" selected>Custom</option>
                            <option value="Google">Google</option>
                             <option value="OpenId">OpenId</option>

                        </select></td>
                </tr>
                <tr><td colspan="=2">
                        <input type="submit" />
                    </td></tr>
            </table>

        </form>  
        </div>
    </body>
</html>


