package nl.han.ica.oose.dea.spotitube.domain;

import java.util.List;

public class Playlists {

    private List<Playlist> playlists;
    private int length;

    public Playlists(List<Playlist> playlists) {
        this.playlists = playlists;
        this.calculateLength();
    }

    public void calculateLength() {
        for(Playlist p : playlists) {
            length += p.getLength();
        }
    }

    public List<Playlist> getPlaylists() {
        return this.playlists;
    }

    public int getLength() {
        return this.length;
    }

}

