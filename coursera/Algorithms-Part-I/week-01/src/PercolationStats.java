import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double F = 1.96;
    private final double[] thresholds;
    private double mean = -1;
    private double stddev = -1;

    public PercolationStats(int n, int trials) {
        // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        thresholds = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            thresholds[i] = 1.0 * percolation.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        // sample mean of percolation threshold
        if (mean < 0) {
            mean = StdStats.mean(thresholds);
        }
        return mean;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        if (stddev < 0) {
            stddev = StdStats.stddev(thresholds);
        }
        return stddev;
    }

    public double confidenceLo() {
        // low  endpoint of 95% confidence interval
        return mean() - (F * Math.sqrt(stddev()) / Math.sqrt(thresholds.length));
    }

    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        return mean() + (F * Math.sqrt(stddev()) / Math.sqrt(thresholds.length));
    }

    public static void main(String[] args) {
        // test client (described below)
        if (args.length >= 2) {
            int n = Integer.parseInt(args[0]);
            int t = Integer.parseInt(args[1]);
            PercolationStats ps = new PercolationStats(n, t);
            StdOut.println("mean                    = " + ps.mean());
            StdOut.println("stddev                  = " + ps.stddev());
            StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
        }
    }
}