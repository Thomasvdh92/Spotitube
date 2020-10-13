package domain;

import nl.han.ica.oose.dea.spotitube.domain.Owner;
import org.junit.Test;

public class TestOwner {

    private Owner owner = new Owner("test", "pass");

    @Test
    public void testConstructor() {
        owner = new Owner();
        owner = new Owner(1,"tester", "newpass");
        assert owner.getPassword().equals("newpass");
        assert owner.getUser().equals("tester");
        owner.setUser("test123");
        owner.setPassword("test123");
        assert owner.getPassword().equals("test123");
        assert owner.getUser().equals("test123");
        assert owner.getId() == 1;
    }
}