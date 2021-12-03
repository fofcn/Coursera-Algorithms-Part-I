/* *****************************************************************************
 *  Name: errorfatal89@gmail.com
 *  Date: 2021.12.02
 *  Description: 快速共线检测算法
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
    private int num = 0;
    private Point[] points;
    private List<LineSegment> segmentList;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
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
        segmentList = new ArrayList<>();
    }

    // the number of line segments
    public int numberOfSegments() {
        return num;
    }

    // the line segments
    public LineSegment[] segments() {
        List<Point[]> startPoints = new ArrayList<>();

        Point[] origin = Arrays.copyOf(points, points.length);
        for (int i = 0; i < points.length; i++) {
            if (i + 1 >= points.length) {
                break;
            }
            Arrays.sort(origin, points[i].slopeOrder());
            // 对排序数组中的相同斜率进行搜索
            // 如果与原点有相同的斜率，那么就记录
            // 如果斜率相同的技术大于等于三，那就存在四个或更多点共线
            // 记录最小点和最大点
            List<Point> foundPoints = new ArrayList<>();
            double startSlop = points[i].slopeTo(origin[i + 1]);
            for (int j = 1; j < origin.length; j++) {
                double slope = points[i].slopeTo(origin[j]);
                // 检查斜率是否与origin数组中的第一个元素的斜率是否相同
                // 如果相同，那么更新最大和最小元素的值
                // 斜率相同的个数加1
                // 记录end的值加1
                // 如果相同数量大于等于3次，那么存在4个共线的点
                if (startSlop == slope) {
                    foundPoints.add(origin[j]);
                } else {
                    addSegment(startPoints, i, foundPoints);
                    foundPoints.clear();
                    foundPoints.add(origin[j]);
                    startSlop = slope;
                }
            }

            addSegment(startPoints, i, foundPoints);
        }

        // 去重
        LineSegment[] result = new LineSegment[segmentList.size()];
        segmentList.toArray(result);
        return result;
    }

    private void addSegment(List<Point[]> startPoints, int i, List<Point> foundPoints) {
        if (foundPoints.size() >= 3) {
            num++;
            foundPoints.add(points[i]);
            Collections.sort(foundPoints);

            // 检查起点是否已经存在
            boolean exists = false;
            for (Point[] point : startPoints) {
                if (point[0].compareTo(foundPoints.get(0)) == 0 &&
                        point[1].compareTo(foundPoints.get(foundPoints.size() - 1)) == 0) {
                    exists = true;
                }
            }

            if (!exists) {
                Point[] segement = new Point[2];
                segement[0] = foundPoints.get(0);
                segement[1] = foundPoints.get(foundPoints.size() - 1);
                startPoints.add(segement);
                LineSegment lineSegment = new LineSegment(foundPoints.get(0),
                                                          foundPoints.get(foundPoints.size() - 1));
                segmentList.add(lineSegment);
            }
        }
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
