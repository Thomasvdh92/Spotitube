package nl.han.ica.oose.dea.spotitube.datasource;

import nl.han.ica.oose.dea.spotitube.domain.Owner;

public interface IOwnerDAO {
    Owner read(String Username);

    Owner read(int id);

    Owner getOwnerByTokenString(String tokenString);

}
