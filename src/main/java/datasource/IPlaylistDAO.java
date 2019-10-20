package datasource;

import domain.Playlist;
import domain.Track;

import java.util.List;

public interface IPlaylistDAO {

    List<Playlist> getAllPlaylists(String token);

    void add(Playlist playlist, String token);

    void delete(int id);

    void put(Playlist playlist);

    void removeTrackFromPlaylist(Integer id, Integer trackid);

    void addTrackToPlaylist(int playlistid, Track track, Boolean OfflineAvailable);
}