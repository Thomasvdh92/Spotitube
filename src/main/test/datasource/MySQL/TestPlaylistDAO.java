package datasource.MySQL;

import datasource.H2Connector;
import nl.han.ica.oose.dea.spotitube.datasource.IOwnerDAO;
import nl.han.ica.oose.dea.spotitube.datasource.IPlaylistDAO;
import nl.han.ica.oose.dea.spotitube.datasource.ITrackDAO;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.IMySQLConnection;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.MySQLOwnerDAO;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.MySQLPlaylistDAO;
import nl.han.ica.oose.dea.spotitube.datasource.MySQL.MySQLTrackDAO;
import nl.han.ica.oose.dea.spotitube.domain.Owner;
import nl.han.ica.oose.dea.spotitube.domain.Playlist;
import nl.han.ica.oose.dea.spotitube.domain.Track;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestPlaylistDAO {

    @Mock
    private Connection connection;

    @Mock
    private IMySQLConnection conn;

    @Mock
    public ITrackDAO trackDAO = new MySQLTrackDAO();

    @Mock
    public IOwnerDAO ownerDAO = new MySQLOwnerDAO();

    @InjectMocks
    public IPlaylistDAO playlistDAO = new MySQLPlaylistDAO(trackDAO, ownerDAO);

    private H2Connector h2 = new H2Connector();

    private String token;

    private Owner owner;

    @Before
    public void setUp() throws FileNotFoundException, EntityNotFoundException {
        connection = h2.getH2Connection();
        when(conn.getConnection()).thenReturn(connection);
        h2.runSqlScript(connection);
        token = "1234-1234-1234";
        owner = new Owner(1, "user", "password");
        when(ownerDAO.getOwnerByTokenString(token)).thenReturn(owner);
    }

    @Test
    public void testGetAllPlaylists() throws EntityNotFoundException {
        List<Playlist> playlistList = playlistDAO.getAllPlaylists(token);
        assert playlistList.size() == 3;

        when(ownerDAO.getOwnerByTokenString(token)).thenReturn(owner);
        playlistList = playlistDAO.getAllPlaylists(token);
        assert playlistList.get(0).isOwner();

        when(ownerDAO.getOwnerByTokenString(token)).thenThrow(EntityNotFoundException.class);
        Exception e = assertThrows(EntityNotFoundException.class, () -> playlistDAO.getAllPlaylists(token));
        assert e.getMessage().equals(String.format("Entity %s not found", Playlist.class.getName()));
    }

    @Test
    public void testAddPlaylist() throws EntityNotFoundException {
        List<Track> newList = new ArrayList<>();
        Playlist p = new Playlist(1, "New playlist", owner, newList);
        playlistDAO.add(p, token);

        List<Playlist> playlistList = playlistDAO.getAllPlaylists(token);
        assert playlistList.size() == 4;
        assert playlistList.get(3).getName() == "New playlist";
    }

    @Test
    public void testDeletePlaylist() throws EntityNotFoundException {
        playlistDAO.delete(1);
        List<Playlist> playlistList = playlistDAO.getAllPlaylists(token);
        assert playlistList.size() == 2;
    }

    @Test
    public void testPutPlaylist() throws EntityNotFoundException {
        Playlist p = new Playlist(1, "new name", false, new ArrayList<>());
        playlistDAO.put(p);
        List<Playlist> playlistList = playlistDAO.getAllPlaylists(token);
        assert playlistList.get(0).getName().equals("new name");
    }

    @After
    public void tearDown() throws SQLException {
        connection.close();
    }
}
