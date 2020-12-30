package morusmath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(FailedTestWatcher.class)
public class CalculatorTest {
    @Test
    public void absoluteTest() {
        Calculator calculator = new Calculator(3);
        assertEquals("10.000", calculator.absolute("10"));
        assertEquals("10.000", calculator.absolute("-10.00000"));

        assertNotEquals("-10.000", calculator.absolute("10"));
        assertNotEquals("-10.000", calculator.absolute("-10.00000"));
    }

    @Test
    public void arcSinTest() {
        Calculator calculator = new Calculator(5);
        assertEquals("0.12377", calculator.arcSin("0.12345"));
        assertNotEquals("0.12378", calculator.arcSin("0.12345"));
    }

    @Test
    public void arcCosTest() {
        Calculator calculator = new Calculator(5);
        assertEquals("1.44702", calculator.arcCos("0.12345"));
        assertNotEquals("1.44703", calculator.arcCos("0.12345"));

        calculator = new Calculator(10);
        assertEquals("1.4470305959", calculator.arcCos("0.12345"));
        assertNotEquals("1.4470305958", calculator.arcCos("0.12345"));
    }

    @Test
    public void addTest() {
        Calculator calculator = new Calculator(3);

        assertEquals("10.000", calculator.add("4", "6"));
        assertEquals("10.000", calculator.add("3.337", "6.6635"));
        assertEquals("0.000", calculator.add("3.337", "-3.337"));
        assertEquals("0.000", calculator.add("0.37", "-0.3704"));
        assertEquals("0.000", calculator.add("0", "0.00"));

        assertNotEquals("10.001", calculator.add("4", "6"));
        assertNotEquals("10.001", calculator.add("3.337", "6.6635"));
        assertNotEquals("0.001", calculator.add("3.337", "-3.337"));
        assertNotEquals("0.001", calculator.add("0.37", "-0.3704"));
        assertNotEquals("0.001", calculator.add("0", "0.00"));

        calculator = new Calculator(0);

        assertEquals("0", calculator.add("-2", "2.2"));
        assertEquals("3", calculator.add("0.458904", "2.541096"));

        assertNotEquals("1", calculator.add("-2", "2.2"));
        assertNotEquals("2", calculator.add("0.458904", "2.541096"));
    }

    @Test
    public void subtractTest() {
        Calculator calculator = new Calculator(5);
        assertEquals("1.23456", calculator.subtract("3.23456", "2"));
        assertEquals("1.00000", calculator.subtract("3.00000", "2"));

        assertNotEquals("-1.23456", calculator.subtract("3.23456", "2"));
        assertNotEquals("-1.00000", calculator.subtract("3.00000", "2"));

        calculator = new Calculator(0);
        assertEquals("1", calculator.subtract("3.23456", "2"));
        assertEquals("1", calculator.subtract("3.00000", "2"));

        assertNotEquals("-1", calculator.subtract("3.23456", "2"));
        assertNotEquals("-1", calculator.subtract("3.00000", "2"));
    }
}
