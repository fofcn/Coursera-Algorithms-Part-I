/* *****************************************************************************
 *  Name: errorfatal89@gmail.com
 *  Date: 2021.11.30
 *  Description: 查找中位数
 *  使用两个最小堆来维护输入数组
 **************************************************************************** */

public class FindMedian<Item extends Comparable<Item>> {

    private MinPQ<Item> large = new MinPQ<>(101);

    private MinPQ<Item> small = new MinPQ<>(101);

    public Item[] findMedian(Item[] a) {
        // 构造两个最小堆
        for (int i = 0; i < a.length; i++) {
            if (small.size() >= large.size()) {
                small.insert(a[i]);
                large.insert(small.delMin());
            } else {
                large.insert(a[i]);
                small.insert(large.delMin());
            }
        }

        Item[] result = (Item[]) new Object[2];
        // 查找中位数
        if (large.size() > small.size()) {
            result[0] = large.delMin();
        } else if (large.size() < small.size()) {
            result[0] = small.delMin();
        } else {
            result[0] = large.delMin();
            result[1] = small.delMin();
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
