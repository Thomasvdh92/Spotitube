package datasource.MySQL;

import datasource.IConnection;
import datasource.ITrackDAO;
import domain.Track;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLTrackDAO implements ITrackDAO {
    @Inject
    private IConnection connection;

    @Override
    public List<Track> tracksForPlaylist(int forPlaylist) {
        Connection conn = connection.getConnection();

        try {
            String query = "SELECT * FROM Track t WHERE NOT EXISTS (SELECT * FROM Playlist_Track pt WHERE t.trackid = pt.trackid AND playlistid = ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, forPlaylist);
            ResultSet rs = stmt.executeQuery();
            List<Track> tracks = new ArrayList<>();
            while(rs.next()) {
                Track t = generateTrackFromResultSet(rs);
                tracks.add(t);
            }
            conn.close();
            for(Track t : tracks) {
                System.out.println(t.getTitle());
            }
            return tracks;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Track> tracksByPlaylistId(int playlistId) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM Track t " +
                            "LEFT JOIN playlist_track pt " +
                            "ON t.trackid = pt.trackid " +
                            "WHERE pt.playlistid = ?");
            stmt.setInt(1, playlistId);
            ResultSet rs = stmt.executeQuery();
            List<Track> tracks = new ArrayList<>();
            while (rs.next()) {
                Track t = generateTrackFromResultSet(rs);
                tracks.add(t);
            }
            conn.close();
            return tracks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Track generateTrackFromResultSet(ResultSet rs) throws SQLException {
        String description = rs.getString(8);
        if(description == null) description = "undefined";
        Track t = new Track(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getString(5),
                rs.getInt(6),
                rs.getString(7),
                description,
                rs.getBoolean(9)
        );
        return t;
    }
}
