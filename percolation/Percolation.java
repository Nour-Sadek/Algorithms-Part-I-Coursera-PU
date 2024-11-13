/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF algo;
    private boolean[][] grid;
    private int size;
    private int numOpen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n cannot be less than or equal to 0");
        size = n;
        numOpen = 0;
        grid = new boolean[n][n];
        algo = new WeightedQuickUnionUF(n * n + 2);
    }

    private boolean areCoordinatesValid(int row, int col) {
        return row <= size && row >= 1 && col <= size && col >= 1;
    }

    private int indexInAlgo(int row, int col) {
        return size * (row - 1) + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (!this.areCoordinatesValid(row, col)) throw new IllegalArgumentException(
                "row and column should be within the bounds of the grid");
        if (this.isOpen(row, col)) return;

        grid[row - 1][col - 1] = true;
        numOpen++;
        int currInAlgo = this.indexInAlgo(row, col);

        // Connect with universal top?
        if (row == 1) algo.union(currInAlgo, 0);
        // Connect with universal bottom?
        if (row == size) algo.union(currInAlgo, size * size + 1);

        // check left of site
        if (col > 1 && this.isOpen(row, col - 1)) algo.union(currInAlgo, currInAlgo - 1);
        // Check right of site
        if (col < size && this.isOpen(row, col + 1)) algo.union(currInAlgo, currInAlgo + 1);
        // Check above site
        if (row > 1 && this.isOpen(row - 1, col)) algo.union(currInAlgo, currInAlgo - size);
        // Check bottom site
        if (row < size && this.isOpen(row + 1, col)) algo.union(currInAlgo, currInAlgo + size);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!this.areCoordinatesValid(row, col)) throw new IllegalArgumentException(
                "row and column should be within the bounds of the grid");
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!this.areCoordinatesValid(row, col)) throw new IllegalArgumentException(
                "row and column should be within the bounds of the grid");
        return algo.find(0) == algo.find(this.indexInAlgo(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return algo.find(0) == algo.find(size * size + 1);
    }

}
