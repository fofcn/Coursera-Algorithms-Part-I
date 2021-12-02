/* *****************************************************************************
 *  Name: errorfatal89@gmail.com
 *  Date: 2021.12.02
 *  Description: 快速共线检测算法
 **************************************************************************** */

import java.util.ArrayList;
import java.util.List;

public class FastCollinearPoints {
    private int num = 0;
    private Point[] points;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        this.points = points;
    }

    // the number of line segments
    public int numberOfSegments() {
        return num;
    }

    // the line segments
    public LineSegment[] segments() {
        List<LineSegment> segmentList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point[] origin = new Point[points.length];
            Point[] aux = new Point[points.length];

            for (int j = i + 1; j < points.length; j++) {
                origin[j] = points[j];
            }

            mergeSort(origin, aux, i + 1, points.length - 1, points[i]);
            if (i + 1 == points.length) {
                break;
            }

            // 对排序数组中的相同斜率进行搜索
            // 如果与原点有相同的斜率，那么就记录
            // 如果斜率相同的技术大于等于三，那就存在四个或更多点共线
            // 记录最小点和最大点
            int sameCnt = 1;
            int start = i;
            int end = i;
            Point smallest = points[i];
            Point largest = points[i];
            double startSlop = points[i].slopeTo(origin[i + 1]);
            boolean found = false;
            for (int j = i + 1; j < origin.length; j++) {
                double slope = points[i].slopeTo(origin[j]);
                // 检查斜率是否与origin数组中的第一个元素的斜率是否相同
                // 如果相同，那么更新最大和最小元素的值
                // 斜率相同的个数加1
                // 记录end的值加1
                // 如果相同数量大于等于3次，那么存在4个共线的点
                if (startSlop == slope) {
                    if (smallest.compareTo(origin[j]) > 0) {
                        smallest = origin[j];
                    }

                    if (largest.compareTo(origin[j]) < 0) {
                        largest = origin[j];
                    }

                    sameCnt++;
                    end++;
                    if (sameCnt >= 3) {
                        found = true;
                        end = j;
                    }
                } else {
                    if (found) {
                        break;
                    }
                    sameCnt = 1;
                    startSlop = slope;
                    if (points[i].compareTo(origin[j]) > 0) {
                        smallest = origin[j];
                    }
                    if (points[i].compareTo(origin[j]) < 0) {
                        largest = origin[j];
                    }
                    start = j;
                    end = j;
                }

            }

            if (found) {
                num++;
                LineSegment lineSegment = new LineSegment(smallest, largest);
                segmentList.add(lineSegment);

                // // 将已经查找到的点交换到i点之后，重新计算i的索引
                // if ((i + 1 + (end - start)) != end) {
                //     for (int j = 0; j < start - i - 1; j++) {
                //         exch(++i, end--);
                //     }
                // }

            }

        }

        LineSegment[] result = new LineSegment[segmentList.size()];
        segmentList.toArray(result);
        return result;
    }

    public void mergeSort(Comparable<Point>[] a, Comparable<Point>[] aux, int start, int end, Point origin) {
        if (a == null) {
            throw new IllegalArgumentException();
        }

        if (end <= start) {
            return;
        }

        // 分解
        int middle = (start + end) / 2;
        mergeSort(a,aux, start, middle, origin);
        mergeSort(a, aux, middle + 1, end, origin);

        merge(a, aux, start, middle, end, origin);
    }

    private void merge(Comparable<Point>[] a, Comparable<Point>[] aux, int start, int middle, int end, Point origin) {
        int left = start;
        int right = middle + 1;

        // 拷贝元素到辅助数组
        for (int i = start; i <= end; i++) {
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
            } else if (less(aux, left, right, origin)) {
                // 比较左右大小，谁小谁拷贝
                a[i] = aux[left];
                left++;
            } else {
                a[i] = aux[right];
                right++;
            }

        }
    }

    private void exch(int i, int j) {
        Point tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    private boolean less(Comparable<Point>[] aux, int left, int right, Point origin) {
        return origin.slopeOrder().compare((Point)aux[left], (Point)aux[right]) < 0;
    }

    public static void main(String[] args) {

    }
}
