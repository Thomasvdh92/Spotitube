package nl.han.ica.oose.dea.spotitube.datasource.MySQL;

import nl.han.ica.oose.dea.spotitube.datasource.IConnection;
import nl.han.ica.oose.dea.spotitube.datasource.ITokenDAO;
import nl.han.ica.oose.dea.spotitube.domain.Token;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MySQLTokenDAO implements ITokenDAO {

    @Inject
    private IConnection connection;

    private final static Logger LOGGER = Logger.getLogger("Logger");

    @Override
    public Token read(String tokenString) throws EntityNotFoundException {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Token_Owner WHERE Token = ?");
            stmt.setString(1, tokenString);
            ResultSet rs = stmt.executeQuery();
            Token t = null;
            while(rs.next()) {
                t = new Token(rs.getString(1), rs.getString(2));
            }
            conn.close();
            if (t == null) {
                throw new EntityNotFoundException(Token.class);
            }
            return t;
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
            return null;
        }
    }

    @Override
    public void insert(Token token) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Token_Owner(Token, Owner) VALUES(?, ?)");
            stmt.setString(1, token.getToken());
            stmt.setString(2, token.getUser());
            stmt.execute();
        } catch (SQLException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
