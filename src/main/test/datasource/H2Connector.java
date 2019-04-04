package datasource;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class H2Connector {



    public Connection getH2Connection() {
        Connection realConn = null;
        try {
            Class.forName("org.h2.Driver");
            realConn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return realConn;
    }

    public void createUserTableAndAddUser(Connection conn) {
        String userTableQuery = "CREATE TABLE `user` (\n" +
                "  `UserID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `username` varchar(100) NOT NULL,\n" +
                "  `password` varchar(100) NOT NULL,\n" +
                "  PRIMARY KEY (`UserID`),\n" +
                "  UNIQUE KEY `Username` (`Username`)\n" +
                ")";

        String insertUserQuery = "INSERT INTO USER(USERNAME,PASSWORD) VALUES('username', 'password')";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE user");
            stmt.executeUpdate(userTableQuery);
            stmt.executeUpdate(insertUserQuery);
            stmt.close();
            System.out.println("Created user table in H2 database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
