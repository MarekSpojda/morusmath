package morusmath;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(FailedTestWatcher.class)
public class ConverterTest {
    @Test
    public void decToBinTest() {
        Converter converter = new Converter();
        assertEquals("1010", converter.decToBin("10"));
    }
}
