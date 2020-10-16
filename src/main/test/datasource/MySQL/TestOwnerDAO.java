package datasource.MySQL;

import datasource.H2Connector;
import nl.han.ica.oose.dea.spotitube.datasource.IOwnerDAO;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.IMySQLConnection;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.MySQLOwnerDAO;
import nl.han.ica.oose.dea.spotitube.domain.Owner;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.sql.Connection;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class TestOwnerDAO {

    @Mock
    private Connection connection;

    @Mock
    private IMySQLConnection conn;

    @InjectMocks
    private IOwnerDAO OwnerDAO = new MySQLOwnerDAO();

    private H2Connector h2 = new H2Connector();

    @Before
    public void setUp() throws FileNotFoundException {
        connection = h2.getH2Connection();
        when(conn.getConnection()).thenReturn(connection);
        h2.runSqlScript(connection);
    }

    @Test
    public void testReadUsername() throws EntityNotFoundException {
        Owner owner = OwnerDAO.read("user");
        assert owner.getUser().equals("user");
        assert owner.getPassword().equals("password");
    }

    @Test
    public void testGetUserByToken() throws EntityNotFoundException {
        Owner owner = OwnerDAO.getOwnerByTokenString("1234-1234-1234");
        assert owner.getUser().equals("user");
        assert owner.getPassword().equals("password");
    }

    @Test
    public void testExceptions() {
        Exception e = assertThrows(EntityNotFoundException.class, () -> OwnerDAO.read("wrong_username"));
        assert e.getMessage().equals(String.format("Entity %s not found", Owner.class.getName()));
        e = assertThrows(EntityNotFoundException.class, () -> OwnerDAO.getOwnerByTokenString("wrong_token_string-123"));
        assert e.getMessage().equals(String.format("Entity %s not found", Owner.class.getName()));
    }
}
