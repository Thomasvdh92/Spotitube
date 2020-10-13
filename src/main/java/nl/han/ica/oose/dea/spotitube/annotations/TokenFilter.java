package nl.han.ica.oose.dea.spotitube.annotations;

import nl.han.ica.oose.dea.spotitube.datasource.ITokenDAO;
import nl.han.ica.oose.dea.spotitube.exceptions.EntityNotFoundException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@TokenRequired
@Priority(Priorities.AUTHENTICATION)
public class TokenFilter implements ContainerRequestFilter {

    @Inject
    ITokenDAO tokenDAO;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        String tokenString = containerRequestContext.getUriInfo().getQueryParameters().getFirst("token");
        try {
            tokenDAO.read(tokenString);
        } catch (EntityNotFoundException e) {
            containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }
}
