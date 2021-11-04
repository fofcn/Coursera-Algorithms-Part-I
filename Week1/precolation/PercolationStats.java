/* *****************************************************************************
 *  Name:              冀全喜
 *  Coursera User ID:  errorfatal89@gmail.com
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CALC_CONST_FRACTION = 1.96;
    private final double[] threshold;
    private final int trials;
    private final double s;
    private double mean;
    private double population;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        validate(n, trials);
        this.threshold = new double[trials];
        this.trials = trials;

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(n);
                int col = StdRandom.uniform(n);
                percolation.open(row + 1, col + 1);
            }
            threshold[i] = percolation.numberOfOpenSites() / ((double) (n * n));
            population += threshold[i];
        }

        mean = population / trials;

        double sum = 0;
        for (int i = 0; i < trials; i++) {
            sum += Math.pow((mean - threshold[i]), 2);
        }

        double sPower = sum / (trials - 1);
        s = Math.sqrt(sPower);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(new double[]{mean});
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - (1.96 * s) / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + (CALC_CONST_FRACTION * s) / Math.sqrt(trials);
    }

    private void validate(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
    }
    // test client (see below)
    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

}