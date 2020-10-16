package nl.han.ica.oose.dea.spotitube.datasource.MySQL;

import javax.enterprise.inject.Default;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

@Default
public class MySQLConnectionFactory implements IMySQLConnection {

    private static Properties prop = new Properties();

    private final static Logger LOGGER = Logger.getLogger("Logger");

    static{
        try {
            prop.load(Objects.requireNonNull(MySQLConnectionFactory.class.getClassLoader().getResourceAsStream("dbproperties.properties")));
            Class.forName(prop.getProperty("jdbc.driver"));
        }catch (ClassNotFoundException | IOException e){
            LOGGER.severe(e.getMessage());
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
