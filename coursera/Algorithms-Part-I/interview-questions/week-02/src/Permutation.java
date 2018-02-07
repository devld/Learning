import java.util.Arrays;

/**
 * <h3>Permutation</h3>
 * 
 * Given two integer arrays of size n,
 * design a subquadratic algorithm to determine
 * whether one is a permutation of the other.
 * That is, do they contain exactly the same entries
 * but, possibly, in a different order.
 */

public class Permutation {

    public static boolean isPermutation(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        int[] ax = Arrays.copyOf(a, a.length);
        int[] bx = Arrays.copyOf(b, b.length);

        Arrays.sort(ax);
        Arrays.sort(bx);

        for (int i = 0; i < ax.length; i++) {
            if (ax[i] != bx[i]) {
                return false;
            }
        }

        return true;
    }

    public static int[] shuffleCopy(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);
        for (int i = 0; i < b.length; i++) {
            int temp = b[i];
            int r = (int) (Math.random() * b.length);
            b[i] = b[r];
            b[r] = temp;
        }
        return b;
    }

    public static void main(String[] args) {

        int[] a = { 1, 1, 4, 46, 3, 75, 7, 5, 7, 12, 31, 41, 4, 1 };
        int[] b = shuffleCopy(a);
        // b[0] = 1234;

        System.out.println(isPermutation(a, b));

    }
}