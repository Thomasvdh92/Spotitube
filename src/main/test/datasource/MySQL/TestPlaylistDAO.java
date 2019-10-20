package datasource.MySQL;

import datasource.H2Connector;
import datasource.IPlaylistDAO;
import domain.Playlist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestPlaylistDAO {

    @Mock
    private Connection connection;

    @Mock
    private IMySQLConnection conn;

    @InjectMocks
    private IPlaylistDAO playlistDAO = new MySQLPlaylistDAO();;

    private H2Connector h2 = new H2Connector();

    @Before
    public void setUp() throws FileNotFoundException {
        connection = h2.getH2Connection();
        when(conn.getConnection()).thenReturn(connection);
        h2.runSqlScript(connection);

    }

    @Test
    public void testGetAllPlaylists() {
        List<Playlist> playlists = playlistDAO.getAllPlaylists("1234-1234-1234");
        playlists.get(0);
        assert true;
    }
}
