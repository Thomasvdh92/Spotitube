package datasource.MySQL;

import datasource.H2Connector;
import nl.han.ica.oose.dea.spotitube.datasource.IOwnerDAO;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.MySQLConnectionFactory;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.MySQLOwnerDAO;
import nl.han.ica.oose.dea.spotitube.domain.Owner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestOwnerDAO {

    IOwnerDAO OwnerDAO;

    Connection connection;

    @InjectMocks
    MySQLConnectionFactory conn;

    H2Connector h2 = new H2Connector();

    @Before
    public void setUp() {
        OwnerDAO = new MySQLOwnerDAO();
        connection = h2.getH2Connection();
        conn = mock(MySQLConnectionFactory.class);
        when(conn.getConnection()).thenReturn(connection);
        h2.createUserTableAndAddUser(connection);
    }

    @After
    public void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        Owner u = OwnerDAO.read("Ownername");
        System.out.println(u.getUsername());
        System.out.println(u.getPassword());
        assert u.getPassword() == "";
    }
}
