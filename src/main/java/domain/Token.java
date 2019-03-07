package domain;

public class Token {

    private String token;
    private String user;

    public Token(String token, String user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
