package domain;

import java.util.List;

public class Playlist {

    private int id;
    private String name;
    private User user;
    private List<Track> tracks;

    public Playlist(int id, String name, User user, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.tracks = tracks;
    }

    public int getLength() {
        int length = 0;
        for(Track t: this.tracks) {
            length += t.getDuration();
        }
        return length;
    }

    public Playlist(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
