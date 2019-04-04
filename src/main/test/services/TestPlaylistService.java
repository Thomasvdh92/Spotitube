package services;

import datasource.IPlaylistDAO;

import datasource.ITrackDAO;
import datasource.IOwnerDAO;
import domain.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class TestPlaylistService {

    @InjectMocks
    PlaylistService playlistService;

    @Mock
    IPlaylistDAO playlistDAO;

    @Mock
    IOwnerDAO OwnerDAO;

    @Mock
    ITrackDAO trackDAO;

    private Playlists playlists;
    private Token token;
    private Playlist p;
    private Owner owner;

    @Before
    public void setUp() throws Exception {
        playlistService = new PlaylistService();
        String tokenString = "1234-1234-1234";
        token = new Token(tokenString, "user");
        owner = mock(Owner.class);
        p = new Playlist(1, "name", owner, null);
        playlists = new Playlists(new ArrayList<>());
        initMocks(this);
        when(OwnerDAO.getOwnerByTokenString(tokenString)).thenReturn(owner);
        when(playlistDAO.getAllPlaylists(tokenString)).thenReturn(new ArrayList<>());
    }

    @Test
    public void testGetPlaylists() {
        Response r = playlistService.getPlaylists(token.getToken());
        assert r.getStatus() == 200;
    }

    @Test
    public void testAddPlaylist() {
        Mockito.doNothing().when(playlistDAO).add(p, token.getToken());
        String body = "{'name':'new-playlist'}";
        Response r = playlistService.addPlaylist(body, token.getToken());
        assert r.getStatus() == 200;
    }

    @Test
    public void testDelete() {
        Mockito.doNothing().when(playlistDAO).delete(1);
        Response r = playlistService.delete(1, token.getToken());
        assert r.getStatus() == 200;
    }

    @Test
    public void testPut() {
        Mockito.doNothing().when(playlistDAO).put(p);
        String body = "{'name':'new-playlist'}";
        Response r = playlistService.put(body, 1, token.getToken());
        assert r.getStatus() == 200;
    }

    @Test
    public void testRemoveTrackFromPlaylist() {
        Mockito.doNothing().when(playlistDAO).removeTrackFromPlaylist(1, 1);
        Response r = playlistService.removeTrackFromPlaylist(1, 1, token.getToken());
        assert r.getStatus() == 200;
    }

    @Test
    public void testGetTracksByPlaylist() {
        when(trackDAO.tracksByPlaylistId(1)).thenReturn(new ArrayList<>());
        Response r = playlistService.getTracksByPlaylist(1);
        assert r.getStatus() == 200;
    }

    @Test
    public void testAddTrackToPlaylist() {
        Track t = new Track();
        String body = "{\n" +
                "  \"id\": 4,\n" +
                "  \"title\": \"So Long, Marianne\",\n" +
                "  \"performer\": \"Leonard Cohen\",\n" +
                "  \"duration\": 546,\n" +
                "  \"album\": \"Songs of Leonard Cohen\",\n" +
                "  \"playcount\": 95,\n" +
                "  \"publicationDate\": undefined,\n" +
                "  \"description\": undefined,\n" +
                "  \"offlineAvailable\": false\n" +
                "}";
        Mockito.doNothing().when(playlistDAO).addTrackToPlaylist(1, t);
        Response r = playlistService.addTrackToPlaylist(1, body, token.getToken());
        assert r.getStatus() == 200;
    }


}
