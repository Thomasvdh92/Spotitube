package services;

import datasource.ITrackDAO;
import domain.Token;
import domain.Tracks;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TestTrackService {

    @InjectMocks
    TrackService trackService;

    @Mock
    ITrackDAO trackDAO;

    @Before
    public void setUp() throws Exception {
        trackService = new TrackService();

        initMocks(this);
    }

    @Test
    public void testGetTracksForPlaylist() {
        Token t = mock(Token.class);
        when(t.getToken()).thenReturn("1234-1234-1234");
        when(trackDAO.tracksForPlaylist(1)).thenReturn(new Tracks(new ArrayList<>()));
        Response r = trackService.getTracksForPlaylist(1, t.getToken());

        assert r.getStatus() == 200;

    }
}
