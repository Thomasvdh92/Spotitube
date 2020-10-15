package nl.han.ica.oose.dea.spotitube.datasource.MySQL;

import nl.han.ica.oose.dea.spotitube.datasource.IOwnerDAO;
import nl.han.ica.oose.dea.spotitube.domain.Owner;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

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
    public Owner read(String Username) throws EntityNotFoundException {
        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM Owner WHERE Username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, Username);
            return getOwner(conn, stmt);
        } catch (SQLException e) {
            throw new EntityNotFoundException(Owner.class);
        }
    }

    @Override
    public Owner getOwnerByTokenString(String tokenString) throws EntityNotFoundException {

        try {
            Connection conn = connection.getConnection();
            String query = "SELECT * FROM Owner O INNER JOIN Token_Owner T_O on O.Username = T_O.Owner WHERE T_O.Token = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tokenString);
            return getOwner(conn, stmt);

        } catch (EntityNotFoundException | SQLException e) {
            throw new EntityNotFoundException(Owner.class);
        }
    }

    private Owner getOwner(Connection conn, PreparedStatement stmt) throws SQLException, EntityNotFoundException {
        ResultSet rs = stmt.executeQuery();
        Owner owner = null;
        while (rs.next()) {
            owner = new Owner(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        conn.close();
        if (owner == null) {
            throw new EntityNotFoundException(Owner.class);
        }
        return owner;
    }
}
