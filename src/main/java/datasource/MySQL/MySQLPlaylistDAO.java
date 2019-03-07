package datasource.MySQL;

import datasource.IConnection;
import datasource.IPlaylistDAO;
import datasource.ITrackDAO;
import datasource.IUserDAO;
import domain.Playlist;
import domain.Track;
import domain.User;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class MySQLPlaylistDAO implements IPlaylistDAO {

    @Inject
    private IConnection connection;

    @Inject
    private ITrackDAO trackDAO;

    @Inject
    private IUserDAO userDAO;

    @Override
    public List<Playlist> getAllPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM Playlist p";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                User u = userDAO.read(rs.getInt(3));
                List<Track> tracks = trackDAO.tracksByPlaylistId(rs.getInt(1));
                Playlist playlist = new Playlist(rs.getInt(1), rs.getString(2), u, tracks);
                playlists.add(playlist);
            }
            conn.close();
            return playlists;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Playlist playlist) {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO Playlist (Name, User) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, playlist.getName());
            stmt.setInt(2, 1);
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Playlist WHERE PlaylistID = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            conn.close();
            System.out.println("Playlist {id} deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
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
            conn.close();
            System.out.println("Playlist {playlist.getId()} updated!");
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public void addTrackToPlaylist(Playlist playlist, Track track) {
        try {
            Connection conn = connection.getConnection();
            String query = "INSERT INTO Playlist_Track(trackid, playlistid) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, track.getId());
            stmt.setInt(2, playlist.getId());
            stmt.executeUpdate();
            playlist.getTracks().add(track);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
