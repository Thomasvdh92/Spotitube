package nl.han.ica.oose.dea.spotitube.datasource;

import nl.han.ica.oose.dea.spotitube.domain.Track;
import nl.han.ica.oose.dea.spotitube.domain.Tracks;

import java.util.List;


public interface ITrackDAO {

    Tracks tracksForPlaylist(int forPlaylist);

    List<Track> tracksByPlaylistId(int playlistId);
}
