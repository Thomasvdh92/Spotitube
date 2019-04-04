package datasource;

import domain.Track;
import domain.Tracks;

import java.util.List;


public interface ITrackDAO {

    Tracks tracksForPlaylist(int forPlaylist);

    List<Track> tracksByPlaylistId(int playlistId);
}
