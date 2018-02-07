import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>Intersection of two sets</h3>
 * 
 * Given two arrays a[] and b[], each containing n distinct 2D points
 * in the plane, design a subquadratic algorithm to count the number of
 * points that are contained both in array a[] and array b[].
 */

public class IntersectionTwoSets {

    public static List<Point> countIntersection(Point[] a, Point[] b) {
        Point[] ax = Arrays.copyOf(a, a.length);
        Point[] bx = Arrays.copyOf(b, b.length);

        Arrays.sort(ax);
        Arrays.sort(bx);

        int i = 0;
        int j = 0;

        List<Point> points = new ArrayList<>();

        while (i < ax.length && j < bx.length) {
            int cmp = ax[i].compareTo(bx[j]);
            if (cmp > 0) {
                j++;
            } else if (cmp < 0) {
                i++;
            } else {
                points.add(ax[i]);
                i++;
                j++;
            }
        }
        return points;
    }

    public static Point[] makePoints(int len, int max) {
        Point[] points = new Point[len];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point((int) (Math.random() * max), (int) (Math.random() * max));
        }
        return points;
    }

    public static void printArray(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 10;
        Point[] a = makePoints(n, n);
        Point[] b = makePoints(n, n);

        System.out.print("a: ");
        printArray(a);
        System.out.print("b: ");
        printArray(b);

        System.out.println(countIntersection(a, b));
    }

    public static class Point implements Comparable<Point> {

        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (x > o.x) {
                return 1;
            } else if (x < o.x) {
                return -1;
            }
            return y - o.y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

    }

}