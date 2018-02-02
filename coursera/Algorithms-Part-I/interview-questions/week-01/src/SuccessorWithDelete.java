/**
 * <h3>Successor with delete</h3>
 * 
 * Given a set of n integers S={0,1,...,n−1} and
 * a sequence of requests of the following form:
 * 
 * - Remove x from S
 * - Find the successor of x: the smallest y in S such that y≥x.
 * 
 * design a data type so that all operations (except construction)
 * take logarithmic time or better in the worst case.
 */

public class SuccessorWithDelete {

    private final int[] precursor;
    private final int[] successor;

    private static final int INVALID = -1;

    public SuccessorWithDelete(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("invalid n");
        }
        precursor = new int[n];
        successor = new int[n];
        for (int i = 0; i < n; i++) {
            precursor[i] = i - 1;
            successor[i] = i + 1;
        }
        successor[n - 1] = INVALID;
    }

    public void remove(int x) {
        int p = precursor[x];
        int s = successor[x];
        if (p >= 0) {
            successor[p] = s;
        }
        if (s >= 0) {
            precursor[s] = p;
        }
        precursor[x] = INVALID;
        successor[x] = INVALID;
    }

    public int successor(int x) {
        return successor[x];
    }

    public static void main(String[] args) {
        SuccessorWithDelete swd = new SuccessorWithDelete(100);
        swd.remove(10);
        swd.remove(11);
        // swd.remove(9);
        System.out.println(swd.successor(9));
    }

}