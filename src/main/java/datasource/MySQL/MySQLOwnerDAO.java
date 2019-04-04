package datasource.MySQL;

import datasource.IOwnerDAO;
import domain.Owner;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class MySQLOwnerDAO implements IOwnerDAO {

    @Inject
    private IMySQLConnection connection;

    @Override
    public Owner read(String Ownername) {
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM Owner WHERE Username = ?";
            System.out.println(query);
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, Ownername);
            return getOwner(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Owner read(int id) {
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM Owner WHERE OwnerID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            return getOwner(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Owner getOwnerByTokenString(String tokenString) {

        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM Owner WHERE EXISTS (SELECT * FROM Token_Owner WHERE Token = ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tokenString);
            return getOwner(conn, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Owner getOwner(Connection conn, PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        Owner Owner = null;
        while (rs.next()) {
            Owner = new Owner(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        conn.close();
        return Owner;
    }
}
