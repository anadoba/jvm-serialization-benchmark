package pl.nadoba.jvm.serialization.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    private static final String TEMP_FILENAME = "tmp";
    private Gson gson = new Gson();
    private ObjectMapper jacksonMapper = new ObjectMapper();
    private JAXBContext jaxbContext;
    private Unmarshaller jaxbUnmarshaller;

    public Serializer() {
        try {
            this.jaxbContext = JAXBContext.newInstance(Login.class);
            this.jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Login processWithSerializable(Login login) {
        Login newLogin = null;

        try {
            FileOutputStream fo = new FileOutputStream(TEMP_FILENAME);
            ObjectOutputStream so = new ObjectOutputStream(fo);
            so.writeObject(login);
            fo.close();
            so.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fi = new FileInputStream(TEMP_FILENAME);
            ObjectInputStream si = new ObjectInputStream(fi);
            newLogin = (Login) si.readObject();
            fi.close();
            si.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newLogin;
    }

    public LoginExternalizable processWithExternalizable(LoginExternalizable login) {
        LoginExternalizable newLogin = null;

        try {
            FileOutputStream fo = new FileOutputStream(TEMP_FILENAME);
            ObjectOutputStream so = new ObjectOutputStream(fo);
            so.writeObject(login);
            fo.close();
            so.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fi = new FileInputStream(TEMP_FILENAME);
            ObjectInputStream si = new ObjectInputStream(fi);
            newLogin = (LoginExternalizable) si.readObject();
            fi.close();
            si.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newLogin;
    }

    public Login processWithGson(Login login) {
        String jsonLogin = gson.toJson(login);
        Login newLogin = gson.fromJson(jsonLogin, Login.class);
        return newLogin;
    }

    public Login processWithJackson(Login login) {
        Login newLogin = null;

        try {
            String jsonLogin = jacksonMapper.writeValueAsString(login);
            newLogin = jacksonMapper.readValue(jsonLogin, Login.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newLogin;
    }

    public Login processWithJaxb(Login login) {
        Login newLogin = null;

        try {
            JAXBElement<Login> jaxbElement = new JAXBElement(new QName("login"), Login.class, login);
            JAXBSource source = new JAXBSource(jaxbContext, jaxbElement);
            JAXBElement<Login> jaxbElementUnmarshalled = jaxbUnmarshaller.unmarshal(source, Login.class);
            newLogin = jaxbElementUnmarshalled.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newLogin;
    }
}
