package morusmath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(FailedTestWatcher.class)
public class CalculatorTest {
    @Test
    public void absoluteTest() {
        Calculator calculator = new Calculator(3);
        assertEquals("10.000", calculator.absolute("10"));
        assertEquals("10.000", calculator.absolute("-10.00000"));
        assertNotEquals("-10.000", calculator.absolute("10"));
        assertNotEquals("-10.000", calculator.absolute("-10.00000"));

        calculator = new Calculator(0);
        assertEquals("10", calculator.absolute("10.1"));
        assertEquals("10", calculator.absolute("-10.12345"));
        assertNotEquals("-10", calculator.absolute("10.02"));
        assertNotEquals("-10", calculator.absolute("-10.5"));
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
    public void arcCosTest() {
        Calculator calculator = new Calculator(5);
        assertEquals("1.44703", calculator.arcCos("0.12345"));
        assertEquals("1.69456", calculator.arcCos("-0.12345"));
        assertEquals("1.57080", calculator.arcCos("0"));
        assertNotEquals("1.44702", calculator.arcCos("0.12345"));
        assertNotEquals("1.69455", calculator.arcCos("-0.12345"));
        assertThrows(ArithmeticException.class, () -> new Calculator(5).arcCos("2"));
        assertThrows(ArithmeticException.class, () -> new Calculator(5).arcCos("-2"));

        calculator = new Calculator(10);
        assertEquals("1.4470305957", calculator.arcCos("0.12345"));
        assertEquals("1.6945620579", calculator.arcCos("-0.12345"));
        assertEquals("0.0000000000", calculator.arcCos("1"));
        assertEquals("0.0000000000", calculator.arcCos("-1"));
        assertEquals("1.5707963268", calculator.arcCos("0.000000"));
        assertNotEquals("1.4470305958", calculator.arcCos("0.12345"));
        assertNotEquals("1.6945620578", calculator.arcCos("-0.12345"));
        assertThrows(ArithmeticException.class, () -> new Calculator(10).arcCos("2"));
        assertThrows(ArithmeticException.class, () -> new Calculator(10).arcCos("-2"));

        calculator = new Calculator(0);
        assertEquals("0", calculator.arcCos("1.000"));
        assertEquals("0", calculator.arcCos("-1.000"));
        assertThrows(ArithmeticException.class, () -> new Calculator(0).arcCos("2"));
        assertThrows(ArithmeticException.class, () -> new Calculator(0).arcCos("-2"));
    }

    @Test
    public void arcSinTest() {
        Calculator calculator = new Calculator(5);
        assertEquals("0.12377", calculator.arcSin("0.12345"));
        assertEquals("1.57080", calculator.arcSin("1"));
        assertEquals("4.71239", calculator.arcSin("-1"));
        assertEquals("0.00000", calculator.arcSin("0"));
        assertEquals("3.26536", calculator.arcSin("-0.12345"));
        assertNotEquals("3.26535", calculator.arcSin("-0.12345"));
        assertNotEquals("-0.12377", calculator.arcSin("-0.12345"));
        assertNotEquals("0.00001", calculator.arcSin("0"));
        assertNotEquals("0.12378", calculator.arcSin("0.12345"));
        assertThrows(ArithmeticException.class, () -> calculator.arcSin("2"));
        assertThrows(ArithmeticException.class, () -> calculator.arcSin("-2"));

        Calculator calculator2 = new Calculator(0);
        assertEquals("0", calculator2.arcSin("0"));
        assertEquals("0", calculator2.arcSin("0.12345"));
        assertEquals("2", calculator2.arcSin("1"));
        assertEquals("5", calculator2.arcSin("-1"));
        assertEquals("3", calculator2.arcSin("-0.12345"));
        assertThrows(ArithmeticException.class, () -> calculator2.arcSin("2"));
        assertThrows(ArithmeticException.class, () -> calculator2.arcSin("-2"));
    }

    @Test
    public void arcTgTest() {
        Calculator calculator = new Calculator(5);
        assertEquals("0.78540", calculator.arcTg("1"));
        assertEquals("0.78540", calculator.arcTg("1.000"));
        assertEquals("2.35619", calculator.arcTg("-1"));
        assertEquals("2.35619", calculator.arcTg("-1.000"));
        assertEquals("0.00000", calculator.arcTg("0"));
        assertEquals("0.00000", calculator.arcTg("0.000"));
        assertEquals("1.16781", calculator.arcTg("2.3456789"));
        assertEquals("-1.16781", calculator.arcTg("-2.3456789"));

        calculator = new Calculator(0);
        assertEquals("1", calculator.arcTg("1"));
        assertEquals("1", calculator.arcTg("1.000"));
        assertEquals("2", calculator.arcTg("-1"));
        assertEquals("2", calculator.arcTg("-1.000"));
        assertEquals("0", calculator.arcTg("0"));
        assertEquals("0", calculator.arcTg("0.000"));
        assertEquals("1", calculator.arcTg("2.3456789"));
        assertEquals("-1", calculator.arcTg("-2.3456789"));
    }

    @Test
    public void divideTest() {
        Calculator calculator = new Calculator(8);
        assertEquals("0.55248619", calculator.divide("1.23456789", "2.23456789"));
        assertEquals("-0.55248619", calculator.divide("-1.23456789", "2.23456789"));
        assertEquals("0.00000000", calculator.divide("0", "2.23456789"));
        assertEquals("8.00000000", calculator.divide("16", "2.000"));
        assertEquals("-8.00000000", calculator.divide("16", "-2.000"));
        assertThrows(ArithmeticException.class, () -> calculator.divide("1", "0"));
        assertDoesNotThrow(() -> calculator.divide("1", "0.00000000000000001"));

        Calculator calculator2 = new Calculator(0);
        assertEquals("3", calculator2.divide("6.1", "2"));
        assertEquals("-3", calculator2.divide("6", "-2.1"));
        assertEquals("0", calculator2.divide("0", "2.23456789"));
        assertEquals("8", calculator2.divide("16", "2.000"));
        assertEquals("-8", calculator2.divide("16", "-2.000"));
        assertThrows(ArithmeticException.class, () -> calculator2.divide("1", "0.00000"));
        assertDoesNotThrow(() -> calculator2.divide("1", "0.00000000000000001"));
    }

    @Test
    public void multiplyTest() {
        Calculator calculator = new Calculator(8);
        assertEquals("2.75872577", calculator.multiply("1.23456789", "2.23456789"));
        assertEquals("8.00000000", calculator.multiply("2", "4"));

        calculator = new Calculator(0);
        assertEquals("-10", calculator.multiply("2", "-5.000"));
    }

    @Test
    public void powerTest() {
        Calculator calculator = new Calculator(10);
        assertEquals("2.8679718603", calculator.power("1.23456789", 5));
        assertEquals("-2.8679718603", calculator.power("-1.23456789", 5));
        assertEquals("0.1250000000", calculator.power("2", -3));
        assertEquals("-0.1250000000", calculator.power("-2", -3));
        assertEquals("1.0000000000", calculator.power("-2", 0));

        calculator = new Calculator(0);
        assertEquals("8", calculator.power("2.001", 3));
        assertEquals("-8", calculator.power("-2", 3));
        assertEquals("1", calculator.power("2", 0));
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
