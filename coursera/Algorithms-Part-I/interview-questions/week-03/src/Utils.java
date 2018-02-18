public class Utils {
    public static int[] randomArray(int n) {
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = (int) (Math.random() * n);
        }
        return r;
    }

    public static int[] randomArray(int n, int min, int max) {
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = (int) (min + Math.random() * max);
        }
        return r;
    }

    public static void printArray(int[] a, int max) {
        int n = max < a.length ? max : a.length;
        System.out.print("size: " + a.length + " [");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i]);
            if (i != n - 1) {
                System.out.print(", ");
            } else {
                System.out.print(a.length > max ? ", ..." : "");
            }
        }
        System.out.println("]");
    }

    public static void printArray(int[] a) {
        printArray(a, 20);
    }

}