import java.util.Arrays;

/**
 * <h3>Merging with smaller auxiliary array</h3>
 * 
 * Suppose that the subarray a[0] to a[n−1] is sorted and
 * the subarray a[n] to a[2∗n−1] is sorted. 
 * How can you merge the two subarrays so that a[0] to a[2∗n−1] 
 * is sorted using an auxiliary array of length n (instead of 2n)?
 */

public class MergeWithSmallerAux {

    public static void merge(int[] a) {
        int n = a.length / 2;
        int[] aux = new int[n];
        System.arraycopy(a, 0, aux, 0, n);
        int i = 0;
        int j = n;
        int k = 0;
        while (k < a.length) {
            if (i >= n) {
                a[k++] = a[j++];
            } else if (j >= a.length) {
                a[k++] = aux[i++];
            } else if (aux[i] > a[j]) {
                a[k++] = a[j++];
            } else {
                a[k++] = aux[i++];
            }
        }
    }

    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 100;
        int[] a1 = Utils.randomArray(n);
        int[] a2 = Utils.randomArray(n);
        Arrays.sort(a1);
        Arrays.sort(a2);
        int[] a = new int[2 * n];
        System.arraycopy(a1, 0, a, 0, n);
        System.arraycopy(a2, 0, a, n, n);

        merge(a);

        assert isSorted(a);

    }

}