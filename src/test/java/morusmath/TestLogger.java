package morusmath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestLogger {
    private static final File reportFile = new File("junitError.log");

    public static void logTestInfo(String message, boolean shallWriteNewLine) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(reportFile, true));
            bufferedWriter.write(message);
            if (shallWriteNewLine) {
                bufferedWriter.write(System.lineSeparator());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
