package datasource;

import domain.Playlist;
import domain.Track;

import java.util.List;

public interface IPlaylistDAO {

    List<Playlist> getAllPlaylists();

    void add(Playlist playlist);

    void delete(int id);

    void put(Playlist playlist);

    void removeTrackFromPlaylist(Integer id, Integer trackid);

    void addTrackToPlaylist(Playlist playlist, Track track);

//    List<Playlist> findByOwner(String owner);
//
//    Playlist findById(int id);
//
//    void save(Playlist playlist);
//
//    void delete(int playlistId);
//
//    void add(Playlist playlist);
}