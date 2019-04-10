/* *****************************************************************************
 *  Name:Weijie.Wu
 *  Date:2/16/2019
 *  Description:PercolationStats
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new java.lang.IllegalArgumentException();
        }

        double[] results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            int result = 0;
            while (!perc.percolates()) {
                int x = StdRandom.uniform(n) + 1;
                int y = StdRandom.uniform(n) + 1;
                while (perc.isOpen(x, y)) {
                    x = StdRandom.uniform(n) + 1;
                    y = StdRandom.uniform(n) + 1;
                }

                perc.open(x, y);
                result++;
            }

            perc = null;
            results[i] = result / (n * n * 1.0);
        }
        mean = StdStats.mean(results);
        stddev = StdStats.stddev(results);
        confidenceLo = mean - CONFIDENCE_95 * stddev / Math.sqrt(trials);
        confidenceHi = mean + CONFIDENCE_95 * stddev / Math.sqrt(trials);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats unitTest = new PercolationStats(n, trials);
        System.out.println("mean    = " + unitTest.mean());
        System.out.println("stddev  = " + unitTest.stddev());
        System.out.println("95% confidence interval = " + unitTest.confidenceLo() + ", " + unitTest.confidenceHi());
    }
}
