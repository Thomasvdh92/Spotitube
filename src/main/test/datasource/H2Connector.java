package datasource;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;

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
        String userTableQuery = "CREATE TABLE `owner` (\n" +
                "  `OwnerID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `Username` varchar(100) NOT NULL,\n" +
                "  `Password` varchar(100) NOT NULL,\n" +
                "  PRIMARY KEY (`OwnerID`),\n" +
//                "  UNIQUE KEY `Username` (`Username`)\n" +
                ")";

        String insertUserQuery = "INSERT INTO Owner(USERNAME,PASSWORD) VALUES('username', 'password')";

        try {
            Statement stmt = conn.createStatement();
            try {
                stmt.executeUpdate("DROP TABLE owner");
            } catch (JdbcSQLSyntaxErrorException e) {
                System.out.println("doing nothing");
            }
            stmt.executeUpdate(userTableQuery);
            stmt.executeUpdate(insertUserQuery);
            stmt.close();
            System.out.println("Created owner table in H2 database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
