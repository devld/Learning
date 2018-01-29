import java.util.Map;
import java.util.concurrent.TimeoutException;

public class App {

    public static void main(String[] args) throws TimeoutException {

        Map<String, Long> result = SortTestUtils.runTest(ThreeWayQuickSort.class);

        for (String item : result.keySet()) {
            System.out.println(item + ": " + result.get(item));
        }

    }

}
