/* *****************************************************************************
 *  Name: errorfatal89@gmail.com
 *  Date: 2021.11.19
 *  Description: 归并排序
 **************************************************************************** */

public class MergeSort<Item> {

    public void mergeSort(Comparable<Item>[] a, Comparable<Item>[] aux, int start, int end) {
        if (a == null) {
            throw new IllegalArgumentException();
        }

        if (start <= end) {
            return;
        }

        // 分解
        int middle = (start + end) / 2;
        mergeSort(a,aux, start, middle);
        mergeSort(a, aux, middle + 1, end);

        merge(a, aux, start, middle, end);
    }

    private void merge(Comparable<Item>[] a, Comparable<Item>[] aux, int start, int middle, int end) {
        int left = start;
        int right = middle + 1;

        // 拷贝元素到辅助数组
        for (int i = start; i < end; i++) {
            aux[i] = a[i];
        }

        for (int i = start; i <= end; i++) {
            if (left > middle) {
                a[i] = aux[right];
                right++;
            } else if (right > end) {
                // 检查右边元素是不是拷贝完成
                a[i] = aux[left];
                left++;
            } else if (aux[left].compareTo(aux[right]) < 0) {
                // 比较左右大小，谁小谁拷贝
                a[i] = aux[left];
                left++;
            } else {
                a[i] = aux[right];
                right++;
            }

        }
    }

    public static void main(String[] args) {

    }
}
