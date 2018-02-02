/**
 * <h3>Union-find with specific canonical element</h3>
 * 
 * Add a method find() to the union-find data type
 * so that find(i) returns the largest element in the connected component containing i.
 * The operations, union(), connected(), and find()
 * should all take logarithmic time or better.
 * 
 * For example, if one of the connected components is {1,2,6,9},
 * then the find() method should return 9 for each of
 * the four elements in the connected components.
 */

public class UnionFindMaxElement {

    private final int[] max;
    private final int[] size;
    private final int[] ids;

    public UnionFindMaxElement(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        ids = new int[n];
        size = new int[n];
        max = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            size[i] = 0;
            max[i] = i;
        }
    }

    private int root(int p) {
        while (p != ids[p]) {
            p = ids[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        if (size[pid] > size[qid]) {
            ids[qid] = pid;
            size[pid] += size[qid];
            if (max[qid] > max[pid]) {
                max[pid] = max[qid];
            }
        } else {
            ids[pid] = qid;
            size[qid] += size[pid];
            if (max[pid] > max[qid]) {
                max[qid] = max[pid];
            }
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int find(int x) {
        return max[root(x)];
    }

    public static void main(String[] args) {
        int n = 10;
        UnionFindMaxElement ufme = new UnionFindMaxElement(n);
        ufme.union(0, 1);
        ufme.union(5, 1);
        ufme.union(6, 5);

        ufme.union(2, 7);

        ufme.union(3, 8);
        ufme.union(4, 9);
        ufme.union(3, 4);

        for (int i = 0; i < n; i++) {
            System.out.println("ufme.find(" + i + ") => " + ufme.find(i));
        }

    }
}