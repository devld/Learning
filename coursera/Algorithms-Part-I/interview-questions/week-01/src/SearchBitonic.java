import java.util.Arrays;

/**
 * <h3>Search in a bitonic array</h3>
 * 
 * An array is bitonic if it is comprised of an increasing sequence of integers
 * followed immediately by a decreasing sequence of integers.
 * Write a program that, given a bitonic array of n distinct integer values,
 * determines whether a given integer is in the array.
 * 
 * - Standard version: Use ∼3lgn compares in the worst case.
 * - Signing bonus: Use ∼2lgn compares in the worst case
 * (and prove that no algorithm can guarantee to perform fewer than ∼2lgn compares in the worst case).
 */

public class SearchBitonic {

    private static int count = 0;

    private static boolean isBitonic(int[] bitonic) {
        boolean desc = false;
        // check duplicates
        int[] temp = Arrays.copyOf(bitonic, bitonic.length);
        Arrays.sort(temp);
        for (int i = 1; i < temp.length; i++) {
            if (temp[i - 1] == temp[i]) {
                return false;
            }
        }
        for (int i = 1; i < bitonic.length; i++) {
            if (desc) {
                if (bitonic[i - 1] < bitonic[i]) {
                    return false;
                }
            } else {
                if (bitonic[i - 1] > bitonic[i]) {
                    desc = true;
                }
            }
        }
        return desc;
    }

    private static int searchAesc(int[] bitonic, int l, int h, int val) {
        while (l <= h) {
            count++;
            int mid = l + (h - l) / 2;
            if (bitonic[mid] == val) {
                return mid;
            } else if (bitonic[mid] > val) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    private static int searchDesc(int[] bitonic, int l, int h, int val) {
        while (l <= h) {
            count++;
            int mid = l + (h - l) / 2;
            if (bitonic[mid] == val) {
                return mid;
            } else if (bitonic[mid] > val) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return -1;
    }

    private static int search(int[] bitonic, int l, int h, int val) {
        count++;
        if (l >= h) {
            return bitonic[l] == val ? l : -1;
        }
        int mid = l + (h - l) / 2;
        if (bitonic[mid] == val) {
            return mid;
        }
        if (mid > 0) {
            int r;
            if (bitonic[mid - 1] < bitonic[mid]) {
                if (bitonic[mid] < val) {
                    return search(bitonic, mid + 1, h, val);
                } else {
                    r = searchAesc(bitonic, l, mid - 1, val);
                    if (r >= 0) {
                        return r;
                    }
                    return search(bitonic, mid + 1, h, val);
                }
            } else {
                if (bitonic[mid] < val) {
                    return search(bitonic, l, mid - 1, val);
                } else {
                    r = searchDesc(bitonic, mid + 1, h, val);
                    if (r >= 0) {
                        return r;
                    }
                    return search(bitonic, l, mid - 1, val);
                }
            }
        } else {
            return bitonic[mid + 1] == val ? (mid + 1) : -1;
        }
    }

    public static int search(int[] bitonic, int val) {
        if (!isBitonic(bitonic)) {
            throw new IllegalArgumentException("not a bitonic array");
        }
        count = 0;
        return search(bitonic, 0, bitonic.length - 1, val);
    }

    public static int[] makeBitonic(int len, int greatIndex, int start) {
        if (len <= 0 || greatIndex <= 0 || greatIndex >= len - 1) {
            throw new IllegalArgumentException("greatIndex should between 1 and len - 2");
        }
        int[] bitonic = new int[len];
        for (int i = 0; i < len; i++) {
            bitonic[i] = start + (int) (1 + Math.random() * 2);
            start = bitonic[i];
        }
        for (int i = 0; i < (len - greatIndex) / 2; i++) {
            int temp = bitonic[greatIndex + i];
            bitonic[greatIndex + i] = bitonic[len - 1 - i];
            bitonic[len - 1 - i] = temp;
        }
        return bitonic;
    }

    private static int test(int len) {
        int[] arr = SearchBitonic.makeBitonic(len, (int) (len * Math.random()), 0);
        int v = (int) (Math.random() * len);
        int r = SearchBitonic.search(arr, arr[v]);
        if (r != v) {
            throw new RuntimeException("found: " + r + ", expected: " + v);
        }
        int optimal = (int) (2 * Math.log(len));
        return SearchBitonic.count - optimal;
    }

    public static void main(String[] args) {
        int t = 100;
        int len = 100000;
        int sum = 0;
        for (int i = 0; i < t; i++) {
            sum += SearchBitonic.test(len);
        }
        System.out.println("average difference<2lg(n)> of compare times: " + sum / t);
    }
}