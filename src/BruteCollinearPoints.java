import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException();
        }

        //check repeated points
        int size = points.length;

        Point[] tempPoints = new Point[size];
        for (int i = 0; i < size; i++) {
            tempPoints[i] = points[i];
        }
        Arrays.sort(tempPoints);
        for (int i = 1; i < size; i++) {
            if (tempPoints[i - 1].compareTo(tempPoints[i]) == 0) throw new IllegalArgumentException();
        }

        double slope1;
        double slope2;
        double slope3;

        lineSegments = new ArrayList<>();

        for (int i = 0; i < size - 3; i++) {
            for (int j = i + 1; j < size - 2; j++) {
                for (int m = j + 1; m < size - 1; m++) {
                    for (int n = m + 1; n < size; n++) {
                        slope1 = tempPoints[i].slopeTo(tempPoints[j]);
                        slope2 = tempPoints[j].slopeTo(tempPoints[m]);
                        slope3 = tempPoints[m].slopeTo(tempPoints[n]);

                        if (slope1 == slope2 && slope2 == slope3) {
                            lineSegments.add(new LineSegment(tempPoints[i], tempPoints[n]));
                        }
                    }
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
//
//        Point a = new Point(1, 2);
//        Point b = new Point(2, 4);
//        Point c = new Point(4, 8);
//        Point d = new Point(8, 16);
//
//        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[]{p, q, r, s, a, b, c, d});
//        System.out.println(bruteCollinearPoints.numberOfSegments());
//        for (LineSegment lineSegment:bruteCollinearPoints.lineSegments){
//            System.out.println(lineSegment);
//        }
    }
}