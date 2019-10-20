package datasource;

import domain.Token;

import java.sql.SQLException;

public interface ITokenDAO {
    Token read(String tokenString);

    void insert(Token token) throws SQLException;
}
