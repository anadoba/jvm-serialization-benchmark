package pl.nadoba.jvm.serialization.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BenchmarkEngine {

    private int objectCount;
    private boolean isPrintEnabled;

    private Random randomizer = new Random();

    public BenchmarkEngine(int objectCount, boolean isPrintEnabled) {
        this.objectCount = objectCount;
        this.isPrintEnabled = isPrintEnabled;
    }

    private List<Login> generateTestList() {
        List<Login> list = new ArrayList<>();

        for (int i=0; i < objectCount; i++) {
            String username = randomizer.ints().toString();
            String password = randomizer.longs().toString();
            Login loginToAdd = new Login(username, password);
            list.add(loginToAdd);
        }

        return list;
    }
}
