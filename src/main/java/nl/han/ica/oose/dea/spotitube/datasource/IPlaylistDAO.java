package nl.han.ica.oose.dea.spotitube.datasource;

import nl.han.ica.oose.dea.spotitube.domain.Playlist;
import nl.han.ica.oose.dea.spotitube.domain.Track;

import java.util.List;

public interface IPlaylistDAO {

    List<Playlist> getAllPlaylists(String token);

    void add(Playlist playlist, String token);

    void delete(int id);

    void put(Playlist playlist);

    void removeTrackFromPlaylist(Integer id, Integer trackid);

    void addTrackToPlaylist(int playlistid, Track track);

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