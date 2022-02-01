package connectDb;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect {
    public Connection connect(){
        try{
            String url = "jdbc:mysql://localhost:3306/SkiLoc";
            String user = "root";
            String password = "toor";

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
