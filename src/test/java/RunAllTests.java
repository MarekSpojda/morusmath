import morusmath.LogFileInitializerTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@SuppressWarnings("JUnit5Platform")
@RunWith(JUnitPlatform.class)
@SelectClasses(LogFileInitializerTest.class)
@SelectPackages("morusmath")
public class RunAllTests {
}