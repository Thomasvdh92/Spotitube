package datasource;

import domain.User;

public interface IUserDAO {
    User read(String username);

    User read(int id);

    User getUserByTokenString(String tokenString);

}
