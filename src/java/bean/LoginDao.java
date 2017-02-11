package bean;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;
import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class LoginDao {

    private static final String NETWORK_NAME = "Google";
    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo?alt=json";
    //private static final Token EMPTY_TOKEN = null;

    public static String validate(LoginBean bean, HttpServletRequest currentRequest, HttpServletResponse currentResponse) {

        switch (bean.getAuthenticationType()) {
            case "Custom":
                return LoginToCustom(bean);
            case "Google":
                return LoginViaGoogle(currentRequest, currentResponse);
             case "OpenId":
                 return LoginViaGoogleAndOpenId(currentRequest, currentResponse);
             default:
                return "Facebbok";
        }

    }

    private static String LoginViaGoogle(HttpServletRequest request, HttpServletResponse response) {
        GoogleAuthHelper helper = new GoogleAuthHelper();
        HttpSession session = request.getSession(true);
        session.setAttribute("AuthType", "Google");
        if (request.getParameter("code") == null
                || request.getParameter("state") == null) {

            session.setAttribute("state", helper.getStateToken());

            String url = helper.buildLoginUrl();
            url = url+"access_type=online&prompt=select_account consent";
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
            }
        } else if (request.getParameter("code") != null && request.getParameter("state") != null
                && request.getParameter("state").equals(session.getAttribute("state"))) {
            session.removeAttribute("state");
            try {
                return helper.getUserInfoJson(request.getParameter("code"));
            } catch (IOException e) {
            }
        }
        return "";
    }

    private static String LoginViaGoogleAndOpenId(HttpServletRequest request, HttpServletResponse response) {
        GoogleOpenIdHelper helper = new GoogleOpenIdHelper();
         HttpSession session = request.getSession(true);
         session.setAttribute("AuthType", "OpenId");

        String url = helper.BuildURL();
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
        }
        return "";
    }

    private static String LoginToCustom(LoginBean bean) {
        String msg = "Error: Invalid username or password";
        boolean status = false;
        try {
            String url = "jdbc:mysql://localhost:3306/customauth";
            String username = "root";
            String password = "P@ssw0rd";

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, username, password);//ConnectionProvider.getCon();

            PreparedStatement ps = con.prepareStatement(
                    "select * from user where username=? and password=?");

            ps.setString(1, bean.getUsername());
            ps.setString(2, bean.getPassword());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                msg = fullName;
            }
            /* status = rs.next();
            if ( status )
                msg = "You are successfully logged In";*/
        } catch (Exception e) {
            // System.err.println("Caught IOException: " + e.getMessage());
            msg = "Error: Invalid username or password";
        }

        return msg;
    }
}
