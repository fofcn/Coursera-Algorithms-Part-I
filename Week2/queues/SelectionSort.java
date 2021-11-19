/* *****************************************************************************
 *  Name: errorfatal89@gmail.com
 *  Date: 2021.11.19
 *  Description: 选择排序
 *  算法分析：
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class SelectionSort {

    public static void sort(Comparable[] a) {
        if (a == null) { // c1 1
            return; // c2 1
        }

        int n = a.length; // c3 1
        for (int i = 0; i < n; i++) { // c4 n
            int smallest = i; // c5 n
            for (int j = i; j < n; j++) { // c6 n
                if (a[j].compareTo(a[smallest]) < 0) { // c7 sigma(j = 1)(n)
                    smallest = j; // c8 sigma(j = 1)(n)
                }
            }

            // 交换i和smallest
            exchange(a, i, smallest); // c9 n

            for (int k = 0; k < n; k++) {
                StdOut.print(a[k] + " ");
            }
            StdOut.println();
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
    }
}
