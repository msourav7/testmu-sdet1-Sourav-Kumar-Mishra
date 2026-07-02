package utils;

import java.io.File;
import java.io.FileWriter;

public class AIReportWriter {

    public static void save(String report) {

        try {

            File folder = new File("reports/ai-summary");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileWriter writer =
                    new FileWriter(
                            "reports/ai-summary/execution-summary.txt"
                    );

            writer.write(report);

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}