import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException();
        }

        //check repeated points
        //copy: the system will check if arg points are mutated.
        //sort: makes comparing and making segment easier
        int size = points.length;
        Point[] orderPoints = new Point[size];
        for (int i = 0; i < size; i++) {
            orderPoints[i] = points[i];
        }
        Arrays.sort(orderPoints);
        for (int i = 1; i < size; i++) {
            if (orderPoints[i - 1].compareTo(orderPoints[i]) == 0) throw new IllegalArgumentException();
        }

        lineSegments = new ArrayList<>();

        //thisPoint： 当前点
        //slopeOrderPoints: 不包含thisPoint的点集，并且在点从小到大排序的基础上进行slope稳定排序
        Point thisPoint;
        Point[] slopeOrderPoints = new Point[size - 1];
        //后面剩余的3个点肯定不用检验了，所以到size-3
        for (int i = 0; i < size - 3; i++) {
            //init: thisPoint and slopeOrderPoints------------------------------------------------
            thisPoint = orderPoints[i];
            for (int j = 0; j < size; j++) {
                if (j < i) slopeOrderPoints[j] = orderPoints[j];
                if (j > i) slopeOrderPoints[j - 1] = orderPoints[j];
            }
            Arrays.sort(slopeOrderPoints, thisPoint.slopeOrder());
            //init done--------------------------------------------------------------------------------


            int collinearNum = 2;
            int start = 0;
            double currentSlope = thisPoint.slopeTo(slopeOrderPoints[start]);
            for (int k = 1; k < size - 1; k++) {
                double slope = thisPoint.slopeTo(slopeOrderPoints[k]);

                if (currentSlope == slope) {
                    collinearNum++;
                } else {
                    if (collinearNum >= 4) {
                        //假如thisPoint比起点小，证明thisPoint是线段的起点而不是在线段的中间，这样可以排除重复线段
                        if (thisPoint.compareTo(slopeOrderPoints[start]) < 0) {
                            lineSegments.add(new LineSegment(thisPoint, slopeOrderPoints[k - 1]));
                        }
                    }
                    start = k;
                    currentSlope = slope;
                    collinearNum = 2;
                }
            }
            if (collinearNum >= 4) {
                //假如该点比起点小，证明该点是线段的起点而不是在线段的中间，排除重复线段
                if (thisPoint.compareTo(slopeOrderPoints[start]) < 0) {
                    lineSegments.add(new LineSegment(thisPoint, slopeOrderPoints[size - 2]));
                }
            }

        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);

    }

    public static void main(String[] args) {
//        Point p = new Point(1, 1);
//        Point q = new Point(2, 2);
//        Point r = new Point(3, 3);
//        Point s = new Point(4, 4);
//        Point t = new Point(5, 5);
//
//        Point a = new Point(1, 2);
//        Point b = new Point(2, 4);
//        Point c = new Point(4, 8);
//        Point d = new Point(8, 16);
//
//        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(new Point[]{p, q, r, s, t, a, b, c, d});
//
//        System.out.println(fastCollinearPoints.numberOfSegments());
//        for (LineSegment lineSegment : fastCollinearPoints.lineSegments) {
//            System.out.println(lineSegment);
//        }
    }
}