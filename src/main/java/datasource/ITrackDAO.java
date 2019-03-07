package datasource;

import domain.Track;

import java.util.List;


public interface ITrackDAO {

    List<Track> tracksForPlaylist(int forPlaylist);

    List<Track> tracksByPlaylistId(int playlistId);
}
