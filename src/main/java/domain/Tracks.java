package domain;

import javax.enterprise.inject.Model;
import java.util.List;

@Model
public class Tracks {
    List<Track> tracks;

    public Tracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Tracks() {}

    public List<Track> getTracks() {
        return tracks;
    }
}
