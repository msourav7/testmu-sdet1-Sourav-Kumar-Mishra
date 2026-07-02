package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AIReportUtil {

    public static String saveAnalysis(String analysis,
                                      String testName) {

        try {

            File folder = new File("reports/ai-analysis");

            folder.mkdirs();

            File report =
                    new File(folder,
                            testName + ".txt");

            FileWriter writer =
                    new FileWriter(report);

            writer.write(analysis);

            writer.close();

            return report.getAbsolutePath();

        }

        catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

}