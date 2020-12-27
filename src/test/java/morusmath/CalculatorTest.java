package morusmath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(FailedTestWatcher.class)
public class CalculatorTest {
    @Test
    public void addTest() {
        Calculator calculator = new Calculator(3);

        assertEquals("10.000", calculator.add("4", "6"));
        assertEquals("10.000", calculator.add("3.337", "6.6635"));
        assertEquals("0.000", calculator.add("3.337", "-3.337"));
        assertEquals("0.000", calculator.add("0.37", "-0.3704"));
        assertEquals("0.000", calculator.add("0", "0.00"));

        calculator = new Calculator(0);

        assertEquals("0", calculator.add("-2", "2.2"));
        assertEquals("3", calculator.add("0.458904", "2.541096"));
    }

    @Test
    public void subtractTest() {
        Calculator calculator = new Calculator(5);
        assertEquals("1.23456", calculator.subtract("3.23456", "2"));
    }
}
