package utils;

import java.util.ArrayList;
import java.util.List;

public class ExecutionCollector {

    private static final List<String> logs = new ArrayList<>();

    public static void log(String message) {

        logs.add(message);

    }

    public static String getLogs() {

        return String.join(System.lineSeparator(), logs);

    }

    public static void clear() {

        logs.clear();

    }

}