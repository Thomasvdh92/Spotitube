package nl.han.ica.oose.dea.spotitube.domain;

import java.util.List;

public class Playlists {

    private List<Playlist> playlists;
    private int length;

    public Playlists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Playlists(){}

    /**
     * Method used to calculate the total length of all playlists inside a Playlists object.
     */
    public void calculateLength() {
        for(Playlist p : playlists) {
            this.length += p.getLength();
        }
    }

    public List<Playlist> getPlaylists() {
        return this.playlists;
    }

    /**
     * Method used to obtain the total duration of all playlists
     * @return
     */
    public int getLength() {
        return this.length;
    }

}

