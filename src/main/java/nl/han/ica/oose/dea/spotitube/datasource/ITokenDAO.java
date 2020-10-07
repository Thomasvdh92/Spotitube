package nl.han.ica.oose.dea.spotitube.datasource;

import nl.han.ica.oose.dea.spotitube.domain.Token;

import java.sql.SQLException;

public interface ITokenDAO {
    Token read(String tokenString);

    void insert(Token token) throws SQLException;
}
