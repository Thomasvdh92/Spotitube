package nl.han.ica.oose.dea.spotitube.domain;


public class Owner {
    private int id;
    private String user;
    private String password;

    public Owner(int id, String user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }

    public Owner(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Owner(){}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
