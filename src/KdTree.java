import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;

public class KdTree {

    private class Node {

        Point2D point;
        boolean isVertical;

        Node left;
        Node right;

        public Node(Point2D point, boolean isVertical, Node left, Node right) {
            this.point = point;
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
        root = insert(root, p, true);
    }

    private Node insert(Node node, Point2D point, boolean isVertical) {
        //if the tree is empty
        if (node == null) {
            size++;
            return new Node(point, isVertical, null, null);
        }

        //if the tree contain point(x,y)
        if (node.point.x() == point.x() && node.point.y() == point.y()) {
            return node;
        }

        //put smaller to the left, and larger to the right
        if ((node.isVertical == true && node.point.x() >= point.x()) || (node.isVertical == false && node.point.y() >= point.y()))
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
            if (current.point.x() == p.x() && current.point.y() == p.y()) return true;

            if (current.isVertical == true && current.point.x() >= p.x() || current.isVertical == false && current.point.y() >= p.y())
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
        if (rect == null) throw new IllegalArgumentException();
        if (root == null) return null;

        ArrayList<Point2D> result = new ArrayList<>();
        ArrayList<Node> handleList = new ArrayList<>();

        handleList.add(root);
        Node current;
        while (!handleList.isEmpty()) {
            current = handleList.remove(0);
            if (rect.contains(current.point)) {
                result.add(current.point);
                if (current.left != null) handleList.add(current.left);
                if (current.right != null) handleList.add(current.right);
            } else {
                if (current.isVertical) {
                    if (rect.xmin() <= current.point.x() && current.left != null) handleList.add(current.left);
                    if (rect.xmax() >= current.point.x() && current.right != null) handleList.add(current.right);
                } else {
                    if (rect.ymin() <= current.point.y() && current.left != null) handleList.add(current.left);
                    if (rect.ymax() >= current.point.y() && current.right != null) handleList.add(current.right);
                }
            }
        }

        return result;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return null;

        return nearest(root, p, null);
    }

    private Point2D nearest(Node startNode, Point2D query, Point2D candidate) {
        if (startNode == null) return candidate;

        Point2D nearestPoint = candidate;
        double nearestDistance = 0;
        double distanceToBound = 0;

        if (nearestPoint != null) {
            nearestDistance = query.distanceSquaredTo(nearestPoint);
            if (startNode.isVertical) {
                distanceToBound = Math.pow(query.x() - startNode.point.x(), 2);
            } else {
                distanceToBound = Math.pow(query.y() - startNode.point.y(), 2);
            }
        }

        if (nearestPoint == null || distanceToBound < nearestDistance) {
            if (nearestPoint == null || query.distanceSquaredTo(startNode.point) < nearestDistance) {
                nearestPoint = startNode.point;
            }
            if (startNode.isVertical) {
                if (query.x() <= startNode.point.x()) {
                    nearestPoint = nearest(startNode.left, query, nearestPoint);
                    nearestPoint = nearest(startNode.right, query, nearestPoint);
                } else {
                    nearestPoint = nearest(startNode.right, query, nearestPoint);
                    nearestPoint = nearest(startNode.left, query, nearestPoint);
                }
            } else {
                if (query.y() <= startNode.point.y()) {
                    nearestPoint = nearest(startNode.left, query, nearestPoint);
                    nearestPoint = nearest(startNode.right, query, nearestPoint);
                } else {
                    nearestPoint = nearest(startNode.right, query, nearestPoint);
                    nearestPoint = nearest(startNode.left, query, nearestPoint);
                }
            }
        }

        return nearestPoint;
    }

    //     unit testing of the methods (optional)
    public static void main(String[] args) {
//        KdTree set = new KdTree();
//        set.insert(new Point2D(0.7, 0.2));
//        set.insert(new Point2D(0.5, 0.4));
//        set.insert(new Point2D(0.2, 0.3));
//        set.insert(new Point2D(0.4, 0.7));
//        set.insert(new Point2D(0.9, 0.6));
//        System.out.println(set.nearest(new Point2D(0.181, 0.441)));


    }
}
