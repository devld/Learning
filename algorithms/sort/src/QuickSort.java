
public final class QuickSort {

    private static int part(int[] s, int l, int h) {
        int i = l;
        int j = h + 1;
        while (true) {
            while (s[++i] < s[l]) {
                if (i >= h) {
                    break;
                }
            }
            while (s[--j] > s[l]) {
                if (j <= l) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(s, i, j);
        }
        swap(s, l, j);
        return j;
    }

    private static void swap(int[] s, int a, int b) {
        int temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }

    private static void sort(int[] s, int l, int h) {
        if (l >= h) {
            return;
        }
        int m = part(s, l, h);
        sort(s, l, m - 1);
        sort(s, m + 1, h);
    }

    public static void sort(int[] s) {
        ArrayUtils.shuffle(s);
        sort(s, 0, s.length - 1);
    }

}