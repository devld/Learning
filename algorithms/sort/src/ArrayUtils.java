public final class ArrayUtils {
    public static void print(Integer[] s) {
        print(s, 20);
    }

    public static void print(Object[] s, int maxNum) {
        maxNum = maxNum > s.length ? s.length : maxNum;
        for (int i = 0; i < maxNum; i++) {
            System.out.print(s[i]);
            if (i != maxNum - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void print(int[] s) {
        print(s, 20);
    }

    public static void print(int[] s, int maxNum) {
        maxNum = maxNum > s.length ? s.length : maxNum;
        for (int i = 0; i < maxNum; i++) {
            System.out.print(s[i]);
            if (i != maxNum - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static boolean isSorted(int[] s) {
        for (int i = 0; i < s.length - 1; i++) {
            if (s[i] > s[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void reverse(int[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            int temp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = temp;
        }
    }

    public static void shuffle(int[] s) {
        for (int i = 0; i < s.length; i++) {
            int r = (int) (Math.random() * i);
            int temp = s[i];
            s[i] = s[r];
            s[r] = temp;
        }
    }

    public static int[] generateRandomArray(int len, int low, int high) {
        if (len < 0) {
            throw new IllegalArgumentException("negative array length");
        }
        int[] r = new int[len];
        for (int i = 0; i < r.length; i++) {
            int item = (int) (Math.random() * (high - low) + low);
            r[i] = item;
        }
        return r;
    }

    public static int[] generateRangeArray(int len, int start) {
        if (len < 0) {
            throw new IllegalArgumentException("negative array length");
        }
        int[] r = new int[len];
        for (int i = 0; i < len; i++) {
            r[i] = start + i;
        }
        return r;
    }

}