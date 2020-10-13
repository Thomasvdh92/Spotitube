package nl.han.ica.oose.dea.spotitube.exceptions;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Class c) {
        super(String.format("Entity %s not found", c.getName()));
    }
}
