package nl.han.ica.oose.dea.spotitube.exceptions;

import javax.ws.rs.core.Response.Status;
import java.io.Serializable;

public class ApplicationException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;
    private Status status;

    public ApplicationException(String msg) {
        super(msg);
        this.status = Status.BAD_REQUEST;
    }
    public ApplicationException(String msg, Status status) {
        super(msg);
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }
}