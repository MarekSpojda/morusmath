package morusmath;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogFileInitializerTest {
    @Test
    public void initLogFile() {
        TestLogger.logTestInfo("", true);
        TestLogger.logTestInfo("Failed tests list - " + new Date().toString(), true);
        TestLogger.logTestInfo("-----------------", true);
        assertTrue(true);
    }
}