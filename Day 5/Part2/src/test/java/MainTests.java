import org.example.Main;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class MainTests {
    @Test
    public void testSwap() {
        Integer[] update = {97, 13, 75, 29, 47};
        Map<Integer, Integer> swapIndex = new HashMap<>();
        swapIndex.put(47, 1);
        swapIndex.put(75, 1);

        Integer[] sorted = Main.swap(update, swapIndex);
        Integer[] answer = {97, 47, 75, 13, 29};

        assertArrayEquals(sorted, answer);
    }
}
