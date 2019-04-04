package nl.han.ica.oose.dea.spotitube.domain;

public class Token {

    private String token;
    private String user;

    public Token(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public Token(){}

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
