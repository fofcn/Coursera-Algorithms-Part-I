/* *****************************************************************************
 *  Name:              冀全喜
 *  Coursera User ID:  errorfatal89@gmail.com
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int cube;
    private int[] normalGrid;
    private int[] normalSize;

    private int[] fullGrid;
    private int[] fullSize;

    private final int n;
    private int numOfOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        cube = n * n;

        normalGrid = new int[cube + 2];
        normalSize = new int[cube + 2];
        initializeGrid(normalGrid, normalSize, cube + 2, true);

        fullGrid = new int[cube + 1];
        fullSize = new int[cube + 1];
        initializeGrid(fullGrid, fullSize, cube + 1, false);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        // 先将块设置为打开
        int index = getIndex(row, col);
        if (normalGrid[index] == -1) {
            numOfOpenSites++;
            // 默认先连接自己
            normalGrid[index] = index;
            fullGrid[index] = index;

            if (row == 1) {
                // 如果是第一行，默认连接虚拟顶部根
                int root = find(normalGrid, n + 1, 1);
                int indexRoot = find(normalGrid, row, col);
                union(normalGrid, normalSize, root, indexRoot);

                // 如果是第一行，默认连接虚拟顶部根
                root = find(fullGrid, n + 1, 1);
                indexRoot = find(fullGrid, row, col);
                union(fullGrid, fullSize, root, indexRoot);
            }

            if (row == n) {
                int root = find(normalGrid, n + 1, 2);
                int indexRoot = find(normalGrid, row, col);
                union(normalGrid, normalSize, root, indexRoot);
            }
        }

        // 查看前后左右是不是有打开的块
        // 如果是第一行打开，那么连接虚拟顶部根
        // 查找上下左右，是否可以进行连接
        // 上
        if (row - 1 >= 1 && isOpen(row - 1, col)) {
            int upRoot = find(normalGrid, row - 1, col);
            int indexRoot = find(normalGrid, row, col);
            union(normalGrid, normalSize, upRoot, indexRoot);

            upRoot = find(fullGrid, row - 1, col);
            indexRoot = find(fullGrid, row, col);
            union(fullGrid, fullSize, upRoot, indexRoot);
        }

        // 下
        if (row + 1 <= n && isOpen(row + 1, col)) {
            int downRoot = find(normalGrid, row + 1, col);
            int indexRoot = find(normalGrid, row, col);
            union(normalGrid, normalSize, downRoot, indexRoot);

            downRoot = find(fullGrid, row + 1, col);
            indexRoot = find(fullGrid, row, col);
            union(fullGrid, fullSize, downRoot, indexRoot);
        }

        // 左
        if (col - 1 >= 1 && isOpen(row, col - 1)) {
            int leftRoot = find(normalGrid, row, col - 1);
            int indexRoot = find(normalGrid, row, col);
            union(normalGrid, normalSize, leftRoot, indexRoot);

            leftRoot = find(fullGrid, row, col - 1);
            indexRoot = find(fullGrid, row, col);
            union(fullGrid, fullSize, leftRoot, indexRoot);
        }

        // 右
        if (col + 1 <= n && isOpen(row, col + 1)) {
            int rightRoot = find(normalGrid, row, col + 1);
            int indexRoot = find(normalGrid, row, col);
            union(normalGrid, normalSize, rightRoot, indexRoot);

            rightRoot = find(fullGrid, row, col + 1);
            indexRoot = find(fullGrid, row, col);
            union(fullGrid, fullSize, rightRoot, indexRoot);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);

        int index = getIndex(row, col);
        return normalGrid[index] != -1;
    }

    // is the site (row, col) full?
    // A full site is an open site that can be </em>
    // connected to an open site in the top row via </em>
    // a chain of neighboring (left, right, up, down) open sites.
    public boolean isFull(int row, int col) {
        validate(row, col);

        return find(fullGrid, n + 1, 1) == find(fullGrid, row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    // A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites
    // We say the system percolates if there is a full site in the bottom row
    public boolean percolates() {
        return find(normalGrid, n + 1, 1) == find(normalGrid, n + 1, 2);
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + col - 1;
    }

    private int find(int[] grid, int row, int col) {
        int index = getIndex(row, col);
        while (grid[index] != -1 && index != grid[index]) {
            index = grid[grid[index]];
        }

        return index;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
    }

    private void union(int [] grid, int[] size, int root, int index) {
        if (root != index) {
            if (grid[index] < size[root]) {
                grid[index] = root;
                size[root] += size[index];
            } else {
                grid[root] = index;
                size[index] += size[root];
            }
        }
    }

    private void initializeGrid(int[] newGrid, int[] newSize, int size, boolean shouldInitBootom) {
        // 初始化虚拟根（顶部和底部）
        // grid[cube]
        for (int i = 0; i < size; i++) {
            newGrid[i] = -1;
            newSize[i] = 1;
        }

        if (shouldInitBootom) {
            newGrid[size - 2] = size - 2;
            newSize[size - 2] = 1;

            // grid[cube + 1]为虚拟底部根
            newGrid[size - 1] = size - 1;
            newSize[size - 1] = 1;
        } else {
            newGrid[size - 1] = size - 1;
            newSize[size - 1] = 1;
        }
    }

    public static void main(String[] args) {

    }
}
