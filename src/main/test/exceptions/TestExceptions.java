package exceptions;

import nl.han.ica.oose.dea.spotitube.exceptions.ApplicationException;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class TestExceptions {

    @Test
    public void testApplicationException() {
        ApplicationException e = new ApplicationException("message");
        assert e.getStatus().equals(Response.Status.BAD_REQUEST);
        assert e.getMessage().equals("message");
    }
}
