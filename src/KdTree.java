import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {

    private class Node {

        double x;
        double y;
        boolean isVertical;

        Node left;
        Node right;

        public Node(double x, double y, boolean isVertical, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.isVertical = isVertical;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private int size;

    // construct an empty set of points
    public KdTree() {
        root = null;
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
//        insert(root, p, true);
        root = insert(root, p, true);
    }

    private Node insert(Node node, Point2D point, boolean isVertical) {
        //if the tree is empty
        if (node == null) {
            size++;
            return new Node(point.x(), point.y(), isVertical, null, null);
        }

        //if the tree contain point(x,y)
        if (node.x == point.x() && node.y == point.y()) {
            return node;
        }


        if ((node.isVertical == true && node.x < point.x()) || (node.isVertical == false && node.y < point.y()))
            node.left = insert(node.left, point, !node.isVertical);

        else
            node.right = insert(node.right, point, !node.isVertical);

        return node;
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        Node current = root;
        while (current != null) {
            if (current.x == p.x() && current.y == p.y()) return true;

            if (current.isVertical == true && current.x < p.x() || current.isVertical == false && current.y < p.y())
                current = current.left;
            else
                current = current.right;
        }

        return false;
    }

    // draw all points to standard draw
    public void draw() {

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
//        KdTree set = new KdTree();
//        set.insert(new Point2D(0.125, 0.3));
//        set.insert(new Point2D(0.3, 0.4));
//        System.out.println(set.size);
//        System.out.println(set.root.x+", "+set.root.y);
//        System.out.println(set.contains(new Point2D(0.125, 0.3)));

    }
}
