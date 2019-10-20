package datasource.MySQL;

import datasource.H2Connector;
import datasource.ITokenDAO;
import domain.Token;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestTokenDAO {

    @Mock
    private Connection connection;

    @Mock
    private IMySQLConnection conn;

    @InjectMocks
    public ITokenDAO tokenDAO = new MySQLTokenDAO();

    private H2Connector h2 = new H2Connector();

    @Before
    public void setUp() throws FileNotFoundException {
        connection = h2.getH2Connection();
        when(conn.getConnection()).thenReturn(connection);
        h2.runSqlScript(connection);
    }

    @Test
    public void testRead() {
        Token t = tokenDAO.read("1234-1234-1234");
        assert t.getUser().equals("user");
        assert t.getToken().equals("1234-1234-1234");
    }

    @Test
    public void testInsert() throws SQLException {
        Token t = new Token("4321-4321-4321", "resu");
        tokenDAO.insert(t);
        Token newToken = tokenDAO.read("4321-4321-4321");
        assert newToken.getToken().equals(t.getToken());
    }
}
