import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int OPEN = 1;
    private final WeightedQuickUnionUF wquf;
    private final int[][] grid;
    private final int n;
    private int openSites = 0;

    public Percolation(int n) {
        // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.grid = new int[n][n];
        this.wquf = new WeightedQuickUnionUF(n * n + 2);
    }

    private void checkSite(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    public void open(int row, int col) {
        // open site (row, col) if it is not open already
        checkSite(row, col);
        row--;
        col--;
        if (this.grid[row][col] != OPEN) {
            this.grid[row][col] = OPEN;
            openSites++;
            int pos = row * n + col + 1;
            if (row > 0) {
                if (isOpen(row - 1 + 1, col + 1)) {
                    wquf.union(pos, (row - 1) * n + col + 1);
                }
            } else if (row == 0) {
                // top
                wquf.union(0, pos);
            }
            if (row < n - 1) {
                if (isOpen(row + 1 + 1, col + 1)) {
                    wquf.union(pos, (row + 1) * n + col + 1);
                }
            } else if (row == n - 1) {
                // bottom
                if (!percolates()) {
                    wquf.union(pos, n * n + 1);
                }
            }
            if (col > 0) {
                if (isOpen(row + 1, col - 1 + 1)) {
                    wquf.union(pos, row * n + (col - 1) + 1);
                }
            }
            if (col < n - 1) {
                if (isOpen(row + 1, col + 1 + 1)) {
                    wquf.union(pos, row * n + (col + 1) + 1);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        // is site (row, col) open?
        checkSite(row, col);
        return this.grid[row - 1][col - 1] == OPEN;
    }

    public boolean isFull(int row, int col) {
        // is site (row, col) full?
        checkSite(row, col);
        return wquf.connected(0, (row - 1) * n + (col - 1) + 1);
    }

    public int numberOfOpenSites() {
        // number of open sites
        return this.openSites;
    }

    public boolean percolates() {
        // does the system percolate?
        return wquf.connected(0, n * n + 1);
    }

    public static void main(String[] args) {
        // test client (optional)
    }
}