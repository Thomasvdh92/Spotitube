package nl.han.ica.oose.dea.spotitube.domain;

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
