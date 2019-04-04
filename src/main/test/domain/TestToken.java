package domain;

import org.junit.Test;

public class TestToken {

    private Token t = new Token("123", "user");

    @Test
    public void testToken() {
        assert t.getToken().equals("123");
        assert t.getUser().equals("user");
        assert t.toString().equals("Token{token='123', user='Owner'}");
    }

}
