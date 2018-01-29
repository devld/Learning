import java.util.Comparator;

public final class MergeSort {

    public static <T> void sort(T[] s, Comparator<? super T> comparator) {
        T[] aux = (T[]) new Object[s.length];
        sort(s, aux, 0, s.length - 1, comparator);
    }

    private static <T> void sort(T[] s, T[] aux, int l, int h, Comparator<? super T> comparator) {
        if (l >= h) {
            return;
        }
        int m = l + (h - l) / 2;
        sort(s, aux, l, m, comparator);
        sort(s, aux, m + 1, h, comparator);
        merge(s, aux, l, h, comparator);
    }

    private static <T> void merge(T[] s, T[] aux, int l, int h, Comparator<? super T> comparator) {
        if (l >= h) {
            return;
        }
        for (int i = l; i <= h; i++) {
            aux[i] = s[i];
        }
        int m = l + (h - l) / 2;
        int i = l;
        int j = m + 1;
        int k = l;
        while (k <= h) {
            if (i > m) {
                s[k++] = aux[j++];
            } else if (j > h) {
                s[k++] = aux[i++];
            } else if (comparator.compare(aux[i], aux[j]) < 0) {
                s[k++] = aux[i++];
            } else {
                s[k++] = aux[j++];
            }
        }
    }

}