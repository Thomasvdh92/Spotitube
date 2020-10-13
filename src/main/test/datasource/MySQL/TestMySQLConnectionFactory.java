package datasource.MySQL;

import nl.han.ica.oose.dea.spotitube.datasource.MySQL.IMySQLConnection;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.MySQLConnectionFactory;
import nl.han.ica.oose.dea.spotitube.domain.Token;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TestMySQLConnectionFactory {
    @Mock
    IMySQLConnection conn;

    @Test
    public void testGetConnection() {
        conn = mock(MySQLConnectionFactory.class);
        when(conn.getConnection()).thenReturn(mock(Connection.class));
        Connection connection = conn.getConnection();
        verify(conn).getConnection();
        assert connection != null;
    }
}
