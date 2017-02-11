package bean;

import java.sql.*;
import static bean.Provider.*;

public class ConnectionProvider {

    private static Connection con = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customauth?" + "user=root&password=P@ssw0rd");
        } catch (Exception e) {
        }
    }

    public static Connection getCon() {
        return con;
    }

}
