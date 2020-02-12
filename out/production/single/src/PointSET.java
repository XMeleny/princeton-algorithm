import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

public class PointSET {
    private SET<Point2D> pointSET;

    // construct an empty set of points
    public PointSET() {
        pointSET = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return pointSET.isEmpty();
    }

    // number of points in the set
    public int size() {
        return pointSET.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        pointSET.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return pointSET.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : pointSET) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        ArrayList<Point2D> result = new ArrayList<>();
        for (Point2D p : pointSET) {
            if (rect.contains(p)) result.add(p);

//            if (rect.xmin() <= p.x() && p.x() <= rect.xmax() && rect.ymin() <= p.y() && p.y() <= rect.ymax()) {
//                result.add(p);
//            }
        }
        return result;

    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Point2D nearestPoint = null;
        double nearestDistance = Double.POSITIVE_INFINITY;

        double distance;
        for (Point2D point2D : pointSET) {
            distance = point2D.distanceSquaredTo(p);
            if (distance < nearestDistance) {
                nearestPoint = point2D;
                nearestDistance = distance;
            }
        }
        return nearestPoint;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
//        PointSET pointSET = new PointSET();
//        pointSET.insert(new Point2D(0.158530, 0.486901));
//        pointSET.insert(new Point2D(0.792202, 0.762825));
//
//        System.out.println(pointSET.nearest(new Point2D(0.158530, 0.486901)));
    }
}
