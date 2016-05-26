package pl.nadoba.jvm.serialization.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BenchmarkEngine {

    private static final int REPEATS = 10000000;

    private int objectCount;
    private boolean isPrintEnabled;

    private Random randomizer = new Random();
    private Serializer serializer = new Serializer();

    private List<Login> testList = generateTestList();
    private List<LoginExternalizable> testExternList = generateTestExternalizableList(testList);

    public BenchmarkEngine(int objectCount, boolean isPrintEnabled) {
        this.objectCount = objectCount;
        this.isPrintEnabled = isPrintEnabled;
    }

    private List<Login> generateTestList() {
        List<Login> list = new ArrayList<>();

        for (int i = 0; i < objectCount; i++) {
            String username = randomizer.ints().toString();
            String password = randomizer.longs().toString();
            Login loginToAdd = new Login(username, password);
            list.add(loginToAdd);
        }

        return list;
    }

    private List<LoginExternalizable> generateTestExternalizableList(List<Login> list) {
        List<LoginExternalizable> externalizableList = new ArrayList<>();

        for (Login login : list) {
            LoginExternalizable loginToAdd = new LoginExternalizable(login.getUsername(), login.getPassword());
            externalizableList.add(loginToAdd);
        }

        return externalizableList;
    }

    public double testSerializable() {
        double beginningNano = System.nanoTime();

        for (int i = 0; i < REPEATS; i++) {
            for (Login login : testList) {
                serializer.processWithSerializable(login);
            }
        }

        double endingNano = System.nanoTime();
        double nanosTaken = endingNano - beginningNano;
        double nanosPerOne = nanosTaken / REPEATS;

        if (isPrintEnabled)
            System.out.println(String.format("Serializable process for %d obj: %f ns", objectCount, nanosPerOne));

        return nanosPerOne;
    }

    public double testExternalizable() {
        double beginningNano = System.nanoTime();

        for (int i = 0; i < REPEATS; i++) {
            for (LoginExternalizable login : testExternList) {
                serializer.processWithExternalizable(login);
            }
        }

        double endingNano = System.nanoTime();
        double nanosTaken = endingNano - beginningNano;
        double nanosPerOne = nanosTaken / REPEATS;

        if (isPrintEnabled)
            System.out.println(String.format("Externalizable process for %d obj: %f ns", objectCount, nanosPerOne));

        return nanosPerOne;
    }

    public double testGson() {
        double beginningNano = System.nanoTime();

        for (int i = 0; i < REPEATS; i++) {
            for (Login login : testList) {
                serializer.processWithGson(login);
            }
        }

        double endingNano = System.nanoTime();
        double nanosTaken = endingNano - beginningNano;
        double nanosPerOne = nanosTaken / REPEATS;

        if (isPrintEnabled)
            System.out.println(String.format("GSON process for %d obj: %f ns", objectCount, nanosPerOne));

        return nanosPerOne;
    }

    public double testJackson() {
        double beginningNano = System.nanoTime();

        for (int i = 0; i < REPEATS; i++) {
            for (Login login : testList) {
                serializer.processWithJackson(login);
            }
        }

        double endingNano = System.nanoTime();
        double nanosTaken = endingNano - beginningNano;
        double nanosPerOne = nanosTaken / REPEATS;

        if (isPrintEnabled)
            System.out.println(String.format("Jackson process for %d obj: %f ns", objectCount, nanosPerOne));

        return nanosPerOne;
    }

    public double testJaxb() {
        double beginningNano = System.nanoTime();

        for (int i = 0; i < REPEATS; i++) {
            for (Login login : testList) {
                serializer.processWithJaxb(login);
            }
        }

        double endingNano = System.nanoTime();
        double nanosTaken = endingNano - beginningNano;
        double nanosPerOne = nanosTaken / REPEATS;

        if (isPrintEnabled)
            System.out.println(String.format("JAX-B process for %d obj: %f ns", objectCount, nanosPerOne));

        return nanosPerOne;
    }
}
