package pl.nadoba.jvm.serialization.benchmark;


import java.io.Serializable;

public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "[Login: " + username + " / " + password + "]";
    }
}
