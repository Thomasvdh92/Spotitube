package nl.han.ica.oose.dea.spotitube.datasource;

import nl.han.ica.oose.dea.spotitube.domain.Playlist;
import nl.han.ica.oose.dea.spotitube.domain.Track;
import nl.han.ica.oose.dea.spotitube.exceptions.ApplicationException;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

import java.util.List;

public interface IPlaylistDAO {

    List<Playlist> getAllPlaylists(String token) throws ApplicationException;

    void add(Playlist playlist, String token) throws ApplicationException;

    void delete(int id);

    void put(Playlist playlist);

    void removeTrackFromPlaylist(Integer id, Integer trackid);

    void addTrackToPlaylist(int playlistid, Track track);
}