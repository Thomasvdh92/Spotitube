package domain;


import java.util.List;

public class Tracks {
    List<Track> tracks;

    public Tracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
