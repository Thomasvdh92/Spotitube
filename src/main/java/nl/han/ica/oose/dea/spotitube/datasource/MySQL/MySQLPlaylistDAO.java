package nl.han.ica.oose.dea.spotitube.datasource.MySQL;

import nl.han.ica.oose.dea.spotitube.datasource.IConnection;
import nl.han.ica.oose.dea.spotitube.datasource.IPlaylistDAO;
import nl.han.ica.oose.dea.spotitube.datasource.ITrackDAO;
import nl.han.ica.oose.dea.spotitube.datasource.IOwnerDAO;
import nl.han.ica.oose.dea.spotitube.domain.Playlist;
import nl.han.ica.oose.dea.spotitube.domain.Track;
import nl.han.ica.oose.dea.spotitube.domain.Owner;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Default
public class MySQLPlaylistDAO implements IPlaylistDAO {

    @Inject
    private IConnection connection;

    @Inject
    private ITrackDAO trackDAO;

    @Inject
    private IOwnerDAO ownerDAO;

    private final static Logger LOGGER = Logger.getLogger("Logger");

    @Inject
    public MySQLPlaylistDAO(ITrackDAO trackDAO, IOwnerDAO ownerDAO) {
        this.trackDAO = trackDAO;
        this.ownerDAO = ownerDAO;
    }

    @Override
    public List<Playlist> getAllPlaylists(String token) throws EntityNotFoundException {
        List<Playlist> playlists = new ArrayList<>();
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM Playlist";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                List<Track> tracks = trackDAO.tracksByPlaylistId(rs.getInt(1));
                boolean isOwner = false;
                Owner currentOwner = ownerDAO.getOwnerByTokenString(token);
                if(currentOwner.getId() == rs.getInt(3)) {
                    isOwner = true;
                }
                Playlist playlist = new Playlist(rs.getInt(1), rs.getString(2), isOwner, tracks);
                playlists.add(playlist);
            }
            return playlists;

        } catch (SQLException | EntityNotFoundException e) {
            // The method "getOwnerByTokenString" throws an EntityNotFoundException if it fails
            throw new EntityNotFoundException(Playlist.class);
        }
    }

    @Override
    public void add(Playlist playlist, String token) throws EntityNotFoundException {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO Playlist (Name, Owner) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, playlist.getName());
            Owner owner = ownerDAO.getOwnerByTokenString(token);
            stmt.setInt(2, owner.getId());
            stmt.executeUpdate();
        } catch (SQLException | EntityNotFoundException e) {
            LOGGER.warning(e.getMessage());
            throw new EntityNotFoundException(Playlist.class);
        }
    }

    @Override
    public void delete(int id) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Playlist WHERE PlaylistID = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void put(Playlist playlist) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Playlist SET Name = ? WHERE PlaylistID = ?");
            stmt.setString(1, playlist.getName());
            stmt.setInt(2, playlist.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void removeTrackFromPlaylist(Integer id, Integer trackid) {
        try {
            Connection conn = connection.getConnection();
            String query = "DELETE FROM Playlist_Track WHERE playlistid = ? AND trackid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setInt(2, trackid);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void addTrackToPlaylist(int playlistid, Track track, Boolean offlineAvailable) {
        try {
            Connection conn = connection.getConnection();
            int bool = (offlineAvailable) ? 1 : 0;
            String query = "UPDATE Track SET offlineAvailable = ? WHERE TrackID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, bool);
            stmt.setInt(2, track.getId());
            stmt.executeUpdate();
            query = "INSERT INTO Playlist_Track(trackid, playlistid) VALUES (?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, track.getId());
            stmt.setInt(2, playlistid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
