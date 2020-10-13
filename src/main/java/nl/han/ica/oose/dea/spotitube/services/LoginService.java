package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.datasource.ITokenDAO;
import nl.han.ica.oose.dea.spotitube.datasource.IOwnerDAO;
import nl.han.ica.oose.dea.spotitube.domain.Owner;
import nl.han.ica.oose.dea.spotitube.domain.Token;
import nl.han.ica.oose.dea.spotitube.exceptions.ApplicationException;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/login")
public class LoginService {

    @Inject
    private IOwnerDAO OwnerDAO;

    @Inject
    private ITokenDAO tokenDAO;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Owner owner) throws ApplicationException {
        try {
            Owner user = OwnerDAO.read(owner.getUser());
            if (!user.getPassword().equals(owner.getPassword())) {
                throw new ApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED);
            }
            Token token = new Token(user.getUser());
            tokenDAO.insert(token);
            return Response.ok(token).build();
        } catch (EntityNotFoundException | SQLException e) {
            throw new ApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED);
        }
    }
}
