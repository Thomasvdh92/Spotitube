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
        assert t.getId() == 1;
        assert t.getTitle().equals("title");
        assert t.getPerformer().equals("performer");
        assert t.getDuration() == 100;
        assert t.getAlbum().equals("album");
        assert t.getPlaycount() == 100;
        assert t.getPublicationDate().equals("12-12-1992");
        assert t.getDescription().equals("track");
        assert t.getOfflineAvailable();

        ArrayList<Track> list = new ArrayList<>();
        tracks = new Tracks(list);
        assert tracks.getTracks() == list;
    }

}
