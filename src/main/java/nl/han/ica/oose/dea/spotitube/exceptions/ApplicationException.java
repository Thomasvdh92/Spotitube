package nl.han.ica.oose.dea.spotitube.exceptions;

import java.io.Serializable;

public class ApplicationException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public ApplicationException(String msg)   {
        super(msg);
    }
}