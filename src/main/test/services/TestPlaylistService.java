package services;

import nl.han.ica.oose.dea.spotitube.datasource.IPlaylistDAO;

import nl.han.ica.oose.dea.spotitube.datasource.ITrackDAO;
import nl.han.ica.oose.dea.spotitube.datasource.IOwnerDAO;

import nl.han.ica.oose.dea.spotitube.domain.*;
import nl.han.ica.oose.dea.spotitube.exceptions.ApplicationException;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;
import nl.han.ica.oose.dea.spotitube.services.PlaylistService;
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
    public void testGetPlaylists() throws ApplicationException {
        Response r = playlistService.getPlaylists(token.getToken());
        assert r.getStatus() == 200;
    }

    @Test
    public void testAddPlaylist() throws EntityNotFoundException, ApplicationException {
        Mockito.doNothing().when(playlistDAO).add(p, token.getToken());
        Response r = playlistService.addPlaylist(p, token.getToken());
        assert r.getStatus() == 201;
    }

    @Test
    public void testDelete() throws ApplicationException {
        Mockito.doNothing().when(playlistDAO).delete(1);
        Response r = playlistService.delete(1, token.getToken());
        assert r.getStatus() == 200;
    }

    @Test
    public void testPut() throws EntityNotFoundException, ApplicationException {
        Mockito.doNothing().when(playlistDAO).put(p);
        Response r = playlistService.put(p, 1, token.getToken());
        assert r.getStatus() == 200;
    }

    @Test
    public void testRemoveTrackFromPlaylist() throws ApplicationException {
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
    public void testAddTrackToPlaylist() throws ApplicationException {
        Track t = new Track(123, "title", "performer", 1, "album", 0, "1-1-2000", "description", false);
        Mockito.doNothing().when(playlistDAO).addTrackToPlaylist(1, t);
        Response r = playlistService.addTrackToPlaylist(1, t, token.getToken());
        assert r.getStatus() == 201;
    }


}
