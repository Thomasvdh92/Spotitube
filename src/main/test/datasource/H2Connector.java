package datasource;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

public class H2Connector {

    public Connection getH2Connection() {
        Connection realConn = null;
        try {
            Class.forName("org.h2.Driver");
            realConn = DriverManager.getConnection("jdbc:h2:~/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE", "sa", "");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return realConn;
    }

    public void runSqlScript(Connection conn) throws FileNotFoundException {
        ScriptRunner sr = new ScriptRunner(conn);
        Reader reader = new BufferedReader(new FileReader("createscript.sql"));
        sr.runScript(reader);
        reader = new BufferedReader(new FileReader("insertscript.sql"));
        sr.runScript(reader);
    }
}
