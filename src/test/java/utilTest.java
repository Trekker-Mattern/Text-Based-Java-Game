import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.textbasedgame.util.TrekkerMath;
import com.textbasedgame.util.selectionMenu;

import java.util.ArrayList;
import java.util.Arrays;
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
        int min = 0;
        int max = 100;
        int iterations = 10000;

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

    @Test
    public void testTrekkerMathRandomIntExtensively(){
        int endVal = 100;
        System.out.println("Testing random integer generation extensively...");

        for(int i = 0; i < endVal; i++){
            testTrekkerMathRandomInt();
        }
        System.out.println("Extensive testing completed successfully!");
    }

    @Test
    public void testTrekkerMathRandomIntSecondTest(){
        for(int i = 0; i < 100; i++){
            int randomValue = TrekkerMath.randomInt(100, 0);
            System.out.println("Random value: " + randomValue);
            assertTrue(randomValue >= 0 && randomValue <= 100, "Random value out of bounds: " + randomValue);
        }
    }
	@Test 
	public void testUISelector(){
		ArrayList<String> list = new ArrayList<>(Arrays.asList("esoteric", "cab", "aab", "con", "contains"));
		System.out.println("Testing UI Selector...");
		assertTrue(selectionMenu.selectScreenToInteger(list, "contains") == list.indexOf("contains")+1);
		assertTrue(selectionMenu.selectScreenToInteger(list, "esoteric") == list.indexOf("esoteric")+1);
		assertTrue(selectionMenu.selectScreenToInteger(list, "2") == 2);
		assertTrue(selectionMenu.selectScreenToInteger(list, "John Cena") == -1);
		assertTrue(selectionMenu.selectScreenToInteger(list, "-5") == -2);
		assertTrue(selectionMenu.selectScreenToInteger(list, "15") == -2);
		assertTrue(selectionMenu.selectScreenToInteger(list, "5") == 5);
		assertTrue(selectionMenu.selectScreenToInteger(list, "0") == -2);
		assertTrue(selectionMenu.selectScreenToInteger(list, "1") == 1);


        System.out.println("All tests passed successfully!");

	}
}
