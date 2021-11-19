/* *****************************************************************************
 *  Name: errorfatal89@gmail.com
 *  Date: 2021.11.19
 *  Description: 希尔排序
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class ShellSort {

    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && a[j].compareTo(a[j - h]) < 0; j -= h) {
                    exchange(a, j, j - h);
                }
            }

            h = h / 3;
        }
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] nums = {7, 1, 0, 3, 2, 5, 4};
        sort(nums);

        for (int i = 0; i < nums.length; i++) {
            StdOut.print(nums[i] + " ");
        }
        StdOut.println();
    }
}
