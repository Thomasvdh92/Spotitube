package nl.han.ica.oose.dea.spotitube.datasource;

import nl.han.ica.oose.dea.spotitube.domain.Owner;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

public interface IOwnerDAO {
    Owner read(String Username) throws EntityNotFoundException;

    Owner getOwnerByTokenString(String tokenString) throws EntityNotFoundException;

}
