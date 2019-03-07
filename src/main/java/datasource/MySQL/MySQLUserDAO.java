package datasource.MySQL;

import datasource.IUserDAO;
import domain.User;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class MySQLUserDAO implements IUserDAO {

    @Inject
    private IMySQLConnection connection;

    @Override
    public User read(String username) {
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM User WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User(rs.getString(2), rs.getString(3));
            }
            conn.close();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User read(int id) {
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM User WHERE UserID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User(rs.getString(2), rs.getString(3));
            }
            conn.close();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByTokenString(String tokenString) {

        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM User WHERE EXISTS (SELECT * FROM Token_User WHERE Token = ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tokenString);
            ResultSet rs = stmt.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User(rs.getString(2), rs.getString(3));
            }
            conn.close();
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
