package nl.han.ica.oose.dea.spotitube.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> {
    @Override
    public Response toResponse(ApplicationException e) {
        return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}