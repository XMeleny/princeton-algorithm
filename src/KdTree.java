import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {

    private class Node implements Comparable<Node> {
        private static final int EVEN = 0;//vertical
        private static final int ODD = 1;//horizontal

        Point2D point;
        int level;

        public Node(Point2D point, int level) {
            this.point = point;
            this.level = level;
        }

        @Override
        public int compareTo(Node that) {
            if (this.level == EVEN) {
                return Double.compare(this.point.x(), that.point.x());
            } else {
                return Double.compare(this.point.y(), that.point.y());
            }
        }
    }

    private SET<Node> set;

    // construct an empty set of points
    public KdTree() {
        set = new SET<>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return set.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        int level = size() % 2;

        set.add(new Node(p,level));
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        //fixme
        return set.contains(new Node(p,0));
    }

    // draw all points to standard draw
    public void draw() {
        for(Node node:set){
            node.point.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        return null;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
