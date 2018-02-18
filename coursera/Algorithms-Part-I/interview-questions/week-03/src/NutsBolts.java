/**
 * <h3>Nuts and bolts</h3>
 * 
 * A disorganized carpenter has a mixed pile of n nuts and n bolts.
 * The goal is to find the corresponding pairs of nuts and bolts.
 * Each nut fits exactly one bolt and each bolt fits exactly one nut.
 * By fitting a nut and a bolt together, the carpenter can see
 * which one is bigger (but the carpenter cannot compare two nuts or two bolts directly).
 * Design an algorithm for the problem that uses nlogn  compares (probabilistically).
 */

public class NutsBolts {

    public static int compareTimes;

    /**
     * make some pairs of nuts and bolts.
     * negative and positive integer represent nut and bolt, 
     * the absolute value start from 1
     * 
     * @param pairs how many pairs
     */

    public static int[][] makeNutsAndBolts(int pairs) {
        int[][] r = new int[2][pairs];

        for (int i = 0; i < pairs; i++) {
            r[0][i] = i;
            r[1][i] = i;
        }

        for (int i = 0; i < r[0].length; i++) {
            int t1 = (int) (Math.random() * i);
            int t2 = (int) (Math.random() * i);

            int temp = r[0][t1];
            r[0][t1] = r[0][i];
            r[0][i] = temp;

            temp = r[1][t2];
            r[1][t2] = r[1][i];
            r[1][i] = temp;
        }

        return r;
    }

    /**
     * compare a nut and bolt.
     * 
     * @param a the object (nut or bolt)
     * @param b the object (nut or bolt)
     * @return 0: they are suitable, 1: a is bigger, -1: b is bigger
     */

    public static int compare(int a, int b) {
        compareTimes++;
        if (a > b) {
            return 1;
        } else if (b > a) {
            return -1;
        } else {
            return 0;
        }
    }

    private static void pair(int[] nuts, int[] bolts, int l, int h) {

        if (l >= h) {
            return;
        }

        int temp = partition(nuts, l, h, bolts[l]);
        partition(bolts, l, h, nuts[temp]);

        pair(nuts, bolts, l, temp - 1);
        pair(nuts, bolts, temp + 1, h);

    }

    private static int partition(int[] n, int l, int h, int pivot) {
        int i = l;
        int c;
        for (int j = l; j < h; j++) {
            c = compare(n[j], pivot);
            if (c < 0) {
                swap(n, i++, j);
            } else if (c == 0) {
                swap(n, j--, h);
            }
        }
        swap(n, i, h);
        return i;
    }

    public static void pair(int[] nuts, int[] bolts) {
        if (nuts.length != bolts.length) {
            throw new IllegalArgumentException();
        }
        pair(nuts, bolts, 0, nuts.length - 1);
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int n = 10;
        int[][] a = makeNutsAndBolts(n);

        Utils.printArray(a[0]);
        Utils.printArray(a[1]);

        compareTimes = 0;
        pair(a[0], a[1]);
        System.out.println("compareTimes = " + compareTimes + ", compareTimes / nlogn = " + (compareTimes / (n * Math.log(n))));

        Utils.printArray(a[0]);
        Utils.printArray(a[1]);
    }

}