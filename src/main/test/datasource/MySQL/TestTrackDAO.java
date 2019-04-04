package datasource.MySQL;

import datasource.H2Connector;
import nl.han.ica.oose.dea.spotitube.datasource.ITrackDAO;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.IMySQLConnection;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.MySQLTrackDAO;
import nl.han.ica.oose.dea.spotitube.domain.Track;
import nl.han.ica.oose.dea.spotitube.domain.Tracks;
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
public class TestTrackDAO {

    @Mock
    private Connection connection;

    @Mock
    private IMySQLConnection conn;

    @InjectMocks
    private ITrackDAO TrackDao = new MySQLTrackDAO();

    private H2Connector h2 = new H2Connector();

    @Before
    public void setUp() throws FileNotFoundException {
        connection = h2.getH2Connection();
        when(conn.getConnection()).thenReturn(connection);
        h2.runSqlScript(connection);
    }

    @Test
    public void testTracksForPlaylist() {
        Tracks tracks = TrackDao.tracksForPlaylist(0);
        Track track = tracks.getTracks().get(0);
        assert track.getTitle().equals("title");
        assert TrackDao.tracksForPlaylist(999) == null;
    }

    @Test
    public void testTracksByPlaylistId() {
        List<Track> tracks = TrackDao.tracksByPlaylistId(1);
        Track track = tracks.get(0);
        assert track.getTitle().equals("title");
        assert TrackDao.tracksByPlaylistId(999) == null;
    }
}
