/* *****************************************************************************
 *  Name: errorfatal89@gmail.com
 *  Date: 2021.11.29
 *  Description: 堆排序
 **************************************************************************** */

public class HeadSort {

    // 算法运行时间分析： 2nlg(n)  theta(nlg(n))
    public static void sort(Comparable[] a) {
        int n = a.length - 1;
        // 自底向上的构建一个最大堆
        // 算法分析 n  / 2
        for (int i = n / 2; i >= 1; i--) {
            // 下沉操作lg(n)
            sink(a, i, n);
        }
        // 总结： theta(nlg(n))

        // 删除最大堆中的第一个元素，并将最后一个元素放到第一个元素位置后对第一个元素执行下沉操作
        // n
        while (n >= 1) {
            // n
            exch(a, 1, n);
            // lg(n)
            sink(a, 1, --n);
        }
        // 总结： theta(nlg(n))
    }

    private static void sink(Comparable[] a, int k, int n) {
        // 根据k查找子节点
        // 2k = 左边子节点
        // 2k+1=右子节点
        while (k * 2 <= n) {
            int j = 2 * k;
            if (j < n && less(a, j, j + 1)) {
                j++;
            }

            if (!less(a, k, j)) {
                break;
            }

            exch(a, k, j);
            k = j;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    public static void main(String[] args) {
        Integer[] a = {null, 1, 5, 3, 2, 4, 6};
        sort(a);
    }
}
