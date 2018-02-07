/**
 * <h3>Dutch national flag</h3>
 * 
 * Given an array of n buckets, each containing a red, white, or blue pebble,
 * sort them by color. The allowed operations are:
 * 
 * - swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 * - color(i): determine the color of the pebble in bucket i.
 * 
 * The performance requirements are as follows:
 * 
 * - At most n calls to color().
 * - At most n calls to swap().
 * - Constant extra space.
 */

public class DutchNationalFlag {

    public static final int RED = 0;
    public static final int WHITE = 1;
    public static final int BLUE = 2;

    public static int colorCalls = 0;
    public static int swapCalls = 0;

    /**
     * sort buckets by color(red, white, blue)
     * like three way quick sort
     * 
     * @param buckets the buckets
     */

    public static void sort(int[] buckets) {
        int red = 0;
        int blue = buckets.length - 1;
        int i = 0;

        while (i <= blue) {
            int color = color(buckets, i);
            if (color == RED) {
                swap(buckets, i++, red++);
            } else if (color == BLUE) {
                // the element at 'i' was swap from unknown bucket, so we cant move 'i' to next
                swap(buckets, i, blue--);
            } else if (color == WHITE) {
                i++;
            }
        }

    }

    private static void swap(int[] buckets, int i, int j) {
        swapCalls++;
        int temp = buckets[i];
        buckets[i] = buckets[j];
        buckets[j] = temp;
    }

    private static int color(int[] buckets, int i) {
        colorCalls++;
        if (buckets[i] < RED || buckets[i] > BLUE) {
            throw new RuntimeException("Unknown color.");
        }
        return buckets[i];
    }

    public static int[] makeDutch(int len) {
        int[] dutch = new int[len];
        for (int i = 0; i < dutch.length; i++) {
            dutch[i] = (int) (Math.random() * (BLUE + 1));
        }
        return dutch;
    }

    public static String colorString(int color) {
        switch (color) {
        case RED:
            return "RED";
        case WHITE:
            return "WHITE";
        case BLUE:
            return "BLUE";
        default:
            throw new RuntimeException("Unknown color.");
        }
    }

    public static void printDutch(int[] dutch) {
        for (int i = 0; i < dutch.length; i++) {
            System.out.print(colorString(dutch[i]));
            if (i != dutch.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] dutch = makeDutch(1000);

        printDutch(dutch);

        sort(dutch);

        printDutch(dutch);

        System.out.println("color() call: " + colorCalls);
        System.out.println("swap()  call: " + swapCalls);

    }

}