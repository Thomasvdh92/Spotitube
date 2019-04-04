package datasource;

import domain.Owner;

public interface IOwnerDAO {
    Owner read(String Ownername);

    Owner read(int id);

    Owner getOwnerByTokenString(String tokenString);

}
