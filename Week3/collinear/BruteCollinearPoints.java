/* *****************************************************************************
 *  Name: errorfatal89@gmail.com
 *  Date: 2021.11.30
 *  Description: 暴力破解 共线问题
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private int num = 0;
    private Point[] points;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }

        // 重复数据直接报错
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        this.points = points;
    }

    // the number of line segments
    public int numberOfSegments() {
        return num;
    }

    // the line segments
    public LineSegment[] segments() {
        List<LineSegment> result = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point first = points[i];
            for (int j = i + 1; j < points.length; j++) {
                Point second = points[j];
                double slop1 = first.slopeTo(second);
                for (int k = j + 1; k < points.length; k++) {
                    Point third = points[k];
                    double slop2 = first.slopeTo(third);
                    for (int l = k + 1; l < points.length; l++) {
                        Point fourth = points[l];
                        // 检查四个点的斜率
                        double slop3 = first.slopeTo(fourth);
                        if (slop1 == slop2 && slop1 == slop3 && slop2 == slop3) {
                            LineSegment lineSegment = createLinearSegments(i, j, k, l);
                            result.add(lineSegment);
                            num++;
                        }
                    }
                }

            }
        }

        LineSegment[] lineSegments = new LineSegment[result.size()];
        return result.toArray(lineSegments);
    }

    private LineSegment createLinearSegments(Integer ... s) {
        // 找出三个点中的Y轴最小和最大的点
        Point smallest = points[s[0]];
        Point largest = points[s[0]];
        for (int i = 1; i < s.length; i++) {
            if (smallest.compareTo(points[s[i]]) > 0) {
                smallest = points[s[i]];
            }

            if (largest.compareTo(points[s[i]]) < 0) {
                largest = points[s[i]];
            }
        }

        return new LineSegment(smallest, largest);
    }

    public static void main(String[] args) {

    }
}
