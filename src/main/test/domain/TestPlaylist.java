package domain;

import nl.han.ica.oose.dea.spotitube.domain.Owner;
import nl.han.ica.oose.dea.spotitube.domain.Playlist;
import nl.han.ica.oose.dea.spotitube.domain.Playlists;
import nl.han.ica.oose.dea.spotitube.domain.Track;
import org.junit.Test;

import java.util.ArrayList;

public class TestPlaylist {

    private Owner u = new Owner("Owner", "Pass");
    private Track t = new Track(1, "title", "performer", 100, "album", 100, "12-12-1992", "track", true);
    private ArrayList<Track> tracks = new ArrayList<>();
    private Playlist p;
    private Playlists playlists = new Playlists();

    @Test
    public void TestPlaylist() {
        tracks.add(t);
        p = new Playlist();
        p = new Playlist(1, "playlist", true, tracks);
        p = new Playlist(1, "playlist", u, tracks);
        p.setId(1);
        p.setName("playlist");
        p.setOwnerName(u);
        p.setOwner(true);
        p.setTracks(tracks);
        assert p.isOwner();
        assert p.getId() == 1;
        assert p.getName().equals("playlist");
        assert p.getOwnerName() == u;
        assert p.getTracks() == tracks;
        assert p.getLength() == 100;
        ArrayList<Playlist> list = new ArrayList<>();
        list.add(p);
        playlists = new Playlists(list);
        assert playlists.getPlaylists() == list;
        playlists.calculateLength();
        assert playlists.getLength() == 100;
    }
}
