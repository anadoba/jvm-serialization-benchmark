package pl.nadoba.jvm.serialization.benchmark;


public class App {
    public static void main(String[] args) {

        if (args.length != 2)
            throw new RuntimeException("This benchmark accepts 2 arguments for each run - amount of loaded objects for tests, true/false flag toggling the 'print' statements.\n" +
                    "For example:\n10000 true");

        int objectCount = Integer.valueOf(args[0]);
        boolean isPrintEnabled = Boolean.valueOf(args[1]);

        BenchmarkEngine engine = new BenchmarkEngine(objectCount, isPrintEnabled);

        engine.testSerializable();
        engine.testExternalizable();
        engine.testJackson();
        engine.testGson();
        engine.testJaxb();
    }
}
