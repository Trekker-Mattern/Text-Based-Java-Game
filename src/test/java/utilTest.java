import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.textbasedgame.util.TrekkerMath;
import java.util.HashMap;

public class utilTest {
    @Test
    public void testTrekkerMathRandomDouble() {
        double min = 1.0;
        double max = 10.0;
        int iterations = 100;

        for (int i = 0; i < iterations; i++) {
            double randomValue = TrekkerMath.randomDouble(max, min);
            assertTrue(randomValue >= min && randomValue <= max, "Random value out of bounds: " + randomValue);
        }

    }

    @Test
    public void testTrekkerMathRandomInt() {
        int min = 1;
        int max = 10;
        int iterations = 1000;

        for (int i = 0; i < iterations; i++) {
            int randomValue = TrekkerMath.randomInt(max, min);
            assertTrue(randomValue >= min && randomValue <= max, "Random value out of bounds: " + randomValue);
        }

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < iterations; i++) {
            int randomValue = TrekkerMath.randomInt(max, min);
            countMap.put(randomValue, countMap.getOrDefault(randomValue, 0) + 1);
        }
        assertTrue(!countMap.isEmpty(), "Expected some random values");

        assertTrue(countMap.size() == max - min, "Expected all values in the range to be generated");

        for (Integer intNumber : countMap.keySet()) {
            int count = countMap.get(intNumber);
            double ratio = (double)count / iterations;
            System.out.println(intNumber + ": " + ratio * 100 + "%");
            double expectedRatio = 1.0 / countMap.size();

            assertTrue(ratio >= expectedRatio - .12 && ratio <= expectedRatio + .12, "Integer " + intNumber + " spawn ratio is out of expected range" + ratio + " expected ratio: " + expectedRatio);
        }
    }
}
