public class ThreeWayQuickSort {

    private static void sort(int[] s, int l, int h) {
        if (l >= h) {
            return;
        }
        int base = s[l];
        int i = l;
        int lt = l;
        int gt = h;
        while (i <= gt) {
            if (s[i] > base) {
                // because we dont know if the element is greater/less than the base element(s[0])
                swap(s, gt--, i);
            } else if (s[i] < base) {
                swap(s, lt++, i++);
            } else {
                i++;
            }
        }
        sort(s, l, lt - 1);
        sort(s, gt + 1, h);
    }

    private static void swap(int[] s, int a, int b) {
        int temp = s[a];
        s[a] = s[b];
        s[b] = temp;
    }

    public static void sort(int[] s) {
        ArrayUtils.shuffle(s);
        sort(s, 0, s.length - 1);
    }

}