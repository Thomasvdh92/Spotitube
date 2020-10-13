package nl.han.ica.oose.dea.spotitube.datasource;

import nl.han.ica.oose.dea.spotitube.domain.Token;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

import java.sql.SQLException;

public interface ITokenDAO {
    Token read(String tokenString) throws EntityNotFoundException;

    void insert(Token token) throws SQLException;
}
