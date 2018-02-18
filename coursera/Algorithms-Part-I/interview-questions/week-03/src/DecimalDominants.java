import java.util.Arrays;
import java.util.HashMap;

/**
 * <h3>Decimal dominants</h3>
 * 
 * Given an array with n keys, design an algorithm to find
 * all values that occur more than n/10 times.
 * The expected running time of your algorithm should be linear.
 */

public class DecimalDominants {

    private static class Element {
        int val = Integer.MIN_VALUE;
        int count = 0;
    }

    public static int[] find(int[] arr, int nBy) {

        Element[] aux = new Element[nBy];
        for (int i = 0; i < aux.length; i++) {
            aux[i] = new Element();
        }

        for (int i = 0; i < arr.length; i++) {
            int index = indexOfAux(aux, arr[i]);
            if (index >= 0) {
                aux[index].count++;
            } else {
                countInAux(aux, arr[i]);
            }
        }

        int total = 0;

        for (int i = 0; i < nBy; i++) {
            aux[i].count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (aux[i].val == arr[j]) {
                    aux[i].count++;
                }
            }
            if (aux[i].count > arr.length / nBy) {
                total++;
            }
        }

        int[] r = new int[total];
        int t = 0;
        for (int i = 0; i < nBy; i++) {
            if (aux[i].count > arr.length / nBy) {
                r[t++] = aux[i].val;
            }
        }

        return r;
    }

    private static void countInAux(Element[] aux, int e) {
        boolean quit = false;
        while (!quit) {
            for (int i = 0; i < aux.length; i++) {
                aux[i].count--;
                if (aux[i].count <= 0) {
                    aux[i].val = e;
                    aux[i].count = 1;
                    quit = true;
                    break;
                }
            }
        }
    }

    private static int indexOfAux(Element[] aux, int e) {
        for (int i = 0; i < aux.length; i++) {
            if (aux[i].val == e) {
                return i;
            } else if (aux[i].count == 0) {
                aux[i].count = 1;
                aux[i].val = e;
                return i;
            }
        }
        return -1;
    }

    public static int[] force(int[] arr, int nBy) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.containsKey(arr[i]) ? (map.get(arr[i]) + 1) : 1);
        }

        int[] r = new int[map.size()];
        int t = 0;
        for (Integer i : map.keySet()) {
            if (map.get(i) > arr.length / nBy) {
                r[t++] = i;
            }
        }

        r = Arrays.copyOf(r, t);

        return r;
    }

    public static void main(String[] args) {

        int[] arr = Utils.randomArray(1000000, 0, 10);
        Utils.printArray(arr);
        int[] r = force(arr, 10);
        Utils.printArray(r);

        int[] r2 = find(arr, 10);
        Utils.printArray(r2);
    }

}