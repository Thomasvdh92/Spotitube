package datasource.MySQL;

import datasource.H2Connector;
import nl.han.ica.oose.dea.spotitube.datasource.ITokenDAO;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.IMySQLConnection;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.MySQLTokenDAO;
import nl.han.ica.oose.dea.spotitube.domain.Owner;
import nl.han.ica.oose.dea.spotitube.domain.Token;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void testRead() throws EntityNotFoundException {
        Token t = tokenDAO.read("1234-1234-1234");
        assert t.getUser().equals("user");
        assert t.getToken().equals("1234-1234-1234");
    }

    @Test
    public void testInsert() throws SQLException, EntityNotFoundException {
        Token t = new Token("4321-4321-4321", "resu");
        tokenDAO.insert(t);
        Token newToken = tokenDAO.read("4321-4321-4321");
        assert newToken.getToken().equals(t.getToken());
    }

    @Test
    public void testExceptions() {
        Exception e = assertThrows(EntityNotFoundException.class, () -> tokenDAO.read("wrong-token-string"));
        assert e.getMessage().equals(String.format("Entity %s not found", Token.class.getName()));
    }
}
