import java.util.Arrays;

/**
 * <h3>Counting inversions</h3>
 * 
 * An inversion in an array a[] is a pair of entries a[i] and a[j] 
 * such that i<j but a[i]>a[j]. Given an array, 
 * design a linearithmic algorithm to count the number of inversions.
 */

public class CountInversion {

    private static int count(int[] a, int[] aux, int l, int h) {
        if (l >= h) {
            return 0;
        }
        int m = l + (h - l) / 2;
        int left = count(a, aux, l, m);
        int right = count(a, aux, m + 1, h);
        int n = 0;

        for (int i = l; i <= h; i++) {
            aux[i] = a[i];
        }

        int i = l;
        int j = m + 1;
        int k = l;

        while (k <= h) {
            if (i > m) {
                a[k++] = aux[j++];
            } else if (j > h) {
                a[k++] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                a[k++] = aux[i++];
            } else {
                n += (m - i + 1);
                a[k++] = aux[j++];
            }
        }

        return left + right + n;
    }

    public static int count(int[] a) {
        int b[] = Arrays.copyOf(a, a.length);
        int aux[] = new int[a.length];
        int n = count(b, aux, 0, a.length - 1);
        return n;
    }

    public static int forceCount(int[] a) {
        int n = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    n++;
                }
            }
        }
        return n;
    }

    public static void main(String[] args) {

        int[] a = Utils.randomArray(10000);

        Utils.printArray(a);

        long tf = System.currentTimeMillis();
        int expected = forceCount(a);
        tf = System.currentTimeMillis() - tf;

        long tc = System.currentTimeMillis();
        int actual = count(a);
        tc = System.currentTimeMillis() - tc;

        assert expected == actual;

        System.out.println("expected: " + expected + " in " + tf);
        System.out.println("value:    " + actual + " in " + tc);

    }

}