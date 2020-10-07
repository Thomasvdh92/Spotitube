package domain;

import nl.han.ica.oose.dea.spotitube.domain.Track;
import nl.han.ica.oose.dea.spotitube.domain.Tracks;
import org.junit.Test;

import java.util.ArrayList;

public class TestTrack {

    private Track t = new Track(1, "title", "performer", 100, "album", 100, "12-12-1992", "track", true);
    private Tracks tracks;

    @Test
    public void testTrackClass() {
        t.setId(1);
        assert t.getId() == 1;
        t.setTitle("title");
        assert t.getTitle().equals("title");
        t.setPerformer("performer");
        assert t.getPerformer().equals("performer");
        t.setDuration(100);
        assert t.getDuration() == 100;
        t.setAlbum("album");
        assert t.getAlbum().equals("album");
        t.setPlaycount(100);
        assert t.getPlaycount() == 100;
        t.setPublicationDate("12-12-1992");
        assert t.getPublicationDate().equals("12-12-1992");
        t.setDescription("track");
        assert t.getDescription().equals("track");
        t.setOfflineAvailable(true);
        assert t.getOfflineAvailable();

        ArrayList<Track> list = new ArrayList<>();
        tracks = new Tracks(list);
        assert tracks.getTracks() == list;
        tracks.setTracks(list);
        assert tracks.getTracks() == list;
    }

}
