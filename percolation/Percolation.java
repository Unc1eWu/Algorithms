/* *****************************************************************************
 *  Name:Weijie.Wu
 *  Date:2/16/2019
 *  Description:Percolation
 **************************************************************************** */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int opened;
    private final WeightedQuickUnionUF uF;
    private final WeightedQuickUnionUF uF1;
    private boolean[][] matrix; // the grid
    private final int n; // the size of the matrix

    /* initialize a n by n matrix */
    public Percolation(int n) {
        if (n < 1) throw new java.lang.IllegalArgumentException();
        this.n = n;
        matrix = new boolean[n + 1][n + 1];
        uF = new WeightedQuickUnionUF(n * n + 2);
        uF1 = new WeightedQuickUnionUF(n * n + 1);
        opened = 0;
    }

    /* open a site in the matrix */
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) throw new java.lang.IllegalArgumentException();
        if (!matrix[row][col]) {
            if (row == 1) {
                uF.union(0, col);
                uF1.union(0, col);
            }

            if (row == n) {
                uF.union(n * n + 1, (row - 1) * n + col);
            }
        }
        matrix[row][col] = true;
        // judge wether this is full or not
        // left
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                uF.union(((row - 1) * n + col), (row - 1) * n + col - 1);
                uF1.union(((row - 1) * n + col), (row - 1) * n + col - 1);
            }
        }

        // right
        if (col < n) {
            if (isOpen(row, col + 1)) {
                uF.union(((row - 1) * n + col), ((row - 1) * n + col + 1));
                uF1.union(((row - 1) * n + col), ((row - 1) * n + col + 1));
            }
        }

        // up
        if (row > 1) {
            if (isOpen(row - 1, col)) {
                uF.union((row - 1) * n + col, (row - 1 - 1) * n + col);
                uF1.union((row - 1) * n + col, (row - 1 - 1) * n + col);
            }
        }

        // down
        if (row < n) {
            if (isOpen(row + 1, col)) {
                uF.union((row - 1) * n + col, (row + 1 - 1) * n + col);
                uF1.union((row - 1) * n + col, (row + 1 - 1) * n + col);
            }
        }
        opened++;
    }

    /* is site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) throw new java.lang.IllegalArgumentException();
        if (matrix[row][col]) return true;
        return false;
    }

    /* is site (row, col) full? */
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) throw new java.lang.IllegalArgumentException();
        if (matrix[row][col])
            if (uF1.connected(0, (row - 1) * n + col)) return true;
        return false;
    }

    /* number of open sites */
    public int numberOfOpenSites() {
        return opened;
    }

    /* does the system percolate? */
    public boolean percolates() {
        return uF.connected(0, n * n + 1);
    }

    /* test client */
    public static void main(String[] args) {
        // for testing
    }
}
