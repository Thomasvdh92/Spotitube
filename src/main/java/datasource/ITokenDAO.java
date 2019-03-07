package datasource;

import domain.Token;

public interface ITokenDAO {
    Token read(String tokenString);

    void insert(Token token);
}
