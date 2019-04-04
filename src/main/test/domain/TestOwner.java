package domain;

import org.junit.Test;

public class TestOwner {

    private Owner owner = new Owner("test", "pass");

    @Test
    public void testConstructor() {
        owner = new Owner("tester", "newpass");
        assert owner.getPassword() == "newpass";
        assert owner.getUsername().equals("tester");
    }
}