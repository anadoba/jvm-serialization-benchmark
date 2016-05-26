package pl.nadoba.jvm.serialization.benchmark;


import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class LoginExternalizable implements Externalizable {

    private String username;
    private String password;

    public LoginExternalizable() {
        super();
    }

    public LoginExternalizable(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "[Login: " + username + " / " + password + "]";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(username);
        out.writeObject(password);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        username = (String) in.readObject();
        password = (String) in.readObject();
    }
}
