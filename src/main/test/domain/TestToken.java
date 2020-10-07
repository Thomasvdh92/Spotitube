package domain;

import nl.han.ica.oose.dea.spotitube.domain.Token;
import org.junit.Test;

public class TestToken {

    private Token t = new Token();

    @Test
    public void testToken() {
        t = new Token("123", "user");
        assert t.getToken().equals("123");
        assert t.getUser().equals("user");
        assert t.toString().equals("Token{token='123', user='user'}");
        t.setToken("234");
        t.setUser("user2");
        assert t.getToken().equals("234");
        assert t.getUser().equals("user2");
    }

}
