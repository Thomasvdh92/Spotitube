package domain;

import org.junit.Test;

public class TestOwner {

    private Owner owner = new Owner("test", "pass");

    @Test
    public void testConstructor() {
        owner = new Owner();
        owner = new Owner(1,"tester", "newpass");
        assert owner.getPassword().equals("newpass");
        assert owner.getUsername().equals("tester");
        owner.setUsername("test123");
        owner.setPassword("test123");
        assert owner.getPassword().equals("test123");
        assert owner.getUsername().equals("test123");
        assert owner.getId() == 1;
    }
}