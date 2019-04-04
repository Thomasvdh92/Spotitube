package domain;

import org.junit.Test;

import java.util.ArrayList;

public class TestPlaylist {

    private Owner u = new Owner("Owner", "Pass");
    private Track t = new Track(1, "title", "performer", 100, "album", 100, "12-12-1992", "track", true);
    private ArrayList<Track> tracks = new ArrayList<>();
    private Playlist p;
    private Playlists playlists;

    @Test
    public void TestPlaylist() {
        tracks.add(t);
        p = new Playlist(1, "playlist", u, tracks);
        assert p.getId() == 1;
        assert p.getName().equals("playlist");
        assert p.getOwnerName() == u;
        assert p.getTracks() == tracks;
        assert p.getLength() == 100;
        ArrayList<Playlist> list = new ArrayList<>();
        list.add(p);
        playlists = new Playlists(list);
        assert playlists.getPlaylists() == list;
        assert playlists.getLength() == 100;
    }
}
