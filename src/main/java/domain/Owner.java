package domain;


public class Owner {
    private int id;
    private String username;
    private String password;

    public Owner(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Owner(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Owner(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
}
