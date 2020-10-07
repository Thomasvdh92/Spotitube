package nl.han.ica.oose.dea.spotitube.datasource.MySQL;

import javax.enterprise.inject.Default;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Default
public class MySQLConnectionFactory implements IMySQLConnection {

    private static Properties prop = new Properties();

    static{
        try {
            prop.load(MySQLConnectionFactory.class.getClassLoader().getResourceAsStream("dbproperties.properties"));
            Class.forName(prop.getProperty("jdbc.driver"));
        }catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        Connection conn;
        try{
            conn = DriverManager.getConnection(prop.getProperty("jdbc.url"));
        }catch (SQLException e){
            throw new IllegalArgumentException("Cannot connect to database!", e);
        }
        return conn;
    }
}
