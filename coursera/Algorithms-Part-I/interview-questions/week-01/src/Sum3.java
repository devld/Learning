import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>3-SUM in quadratic time</h3>
 * 
 * Design an algorithm for the [3-SUM](https://en.wikipedia.org/wiki/3SUM) problem that
 * takes time proportional to n2 in the worst case.
 * You may assume that you can sort the n integers
 * in time proportional to n^2 or better.
 */

public class Sum3 {

    public static List<int[]> solution(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);
        Arrays.sort(b);
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < b.length; i++) {
            int start = i + 1;
            int end = b.length - 1;
            while (start < end) {
                int t = b[i] + b[start] + b[end];
                if (t == 0) {
                    result.add(new int[] { b[i], b[start], b[end--] });
                } else if (t < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }

    public static int[] randomArray(int len, int start, int end) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = start + (int) (Math.random() * (end - start));
        }
        return arr;
    }

    private static void printSolution(int[] a, List<int[]> r) {
        for (int i = 0; i < r.size(); i++) {
            int[] temp = r.get(i);
            System.out.print("[");
            for (int j = 0; j < temp.length; j++) {
                System.out.print(temp[j]);
                if (j != temp.length - 1) {
                    System.out.print(", ");
                } else {
                    System.out.print("]");
                }
            }
            if (i != r.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = randomArray(50, -500, 500);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i != a.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        List<int[]> r1 = solution(a);
        System.out.println("solution1: " + r1.size());
        printSolution(a, r1);
    }
}