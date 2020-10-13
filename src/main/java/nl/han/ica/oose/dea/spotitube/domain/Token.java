package nl.han.ica.oose.dea.spotitube.domain;

import java.util.Random;

public class Token {

    private String token;
    private String user;

    public Token(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public Token(String user) {
        this.token = GenerateToken(user);
        this.user = user;
    }

    public Token(){}

    public String GenerateToken(String user) {
        Random rand = new Random();
        StringBuilder tokenString = new StringBuilder();
        // Generate a random string used for the token
        for (int i = 1; i < 15; i++) {
            if (i % 5 == 0) tokenString.append("-");
            else {
                int n = rand.nextInt(10);
                tokenString.append(n);
            }
        }
        return tokenString.toString();
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
