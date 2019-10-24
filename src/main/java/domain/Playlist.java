package domain;

import java.util.List;

public class Playlist {

    private int id;
    private String name;
    private boolean owner;
    private List<Track> tracks;
    private Owner ownerName;

    public Playlist(int id, String name, Owner ownerName, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.tracks = tracks;
    }

    public Playlist(int id, String name, boolean owner, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.tracks = tracks;
        this.owner = owner;
    }

    /**
     * Method used to calculate the total length of all tracks inside a Playlist object
     * @return
     */
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

    public List<Track> getTracks() {
        return tracks;
    }

    public boolean isOwner() {
        return owner;
    }

    public Owner getOwnerName() {
        return ownerName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void setOwnerName(Owner ownerName) {
        this.ownerName = ownerName;
    }
}
