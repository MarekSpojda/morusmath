package morusmath;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FailedTestWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Optional<Class<?>> optionalClass = context.getTestClass();
        String className = "";
        if (optionalClass.isPresent()) {
            className = optionalClass.get().getName();
        }
        String testName = context.getDisplayName().replace("(", "").replace(")", "");
        TestLogger.logTestInfo(context.getDisplayName() + " in ", false);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        cause.printStackTrace(printWriter);
        TestLogger.logTestInfo(
                getLine(className, testName, stringWriter.toString()), true);
        TestLogger.logTestInfo(cause.getMessage(), true);
    }

    private String getLine(String className, String testName, String textToSearch) {
        String lineRegex =
                "at " + className + "\\." + testName + "\\(" + className + "\\.java:[0-9]+\\)";
        Pattern linePattern = Pattern.compile(lineRegex);
        Matcher lineMatcher = linePattern.matcher(textToSearch);
        if (lineMatcher.find()) {
            String matchFound = lineMatcher.group();
            matchFound = matchFound.replace("at " + className + "." + testName + "(", "");
            matchFound = matchFound.substring(0, matchFound.length() - 1);
            return matchFound;
        }
        return "(unable to get failed test info)";
    }
}