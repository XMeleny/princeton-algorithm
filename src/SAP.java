import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;

public class SAP {
    private final Digraph digraph;
    private final Iterable<Integer> path;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();
        digraph = new Digraph(G);
        DepthFirstOrder dfo = new DepthFirstOrder(digraph);
        path = dfo.reversePost();
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(digraph, w);

        int result = Integer.MAX_VALUE;

        for (int item : path) {
            if (V.hasPathTo(item) && W.hasPathTo(item)) {
                int dis = V.distTo(item) + W.distTo(item);
                result = Math.min(dis, result);
            }
        }
        return result < Integer.MAX_VALUE ? result : -1;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(digraph, w);

        int mindis = Integer.MAX_VALUE;
        int result = -1;


        for (int item : path) {
            if (V.hasPathTo(item) && W.hasPathTo(item)) {
                int dis = V.distTo(item) + W.distTo(item);
                if (dis < mindis) {
                    mindis = dis;
                    result = item;
                }
            }
        }
        return result;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();

        BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(digraph, w);

        int result = Integer.MAX_VALUE;

        for (int item : path) {
            if (V.hasPathTo(item) && W.hasPathTo(item)) {
                int dis = V.distTo(item) + W.distTo(item);
                result = Math.min(dis, result);
            }
        }

        return result < Integer.MAX_VALUE ? result : -1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();

        BreadthFirstDirectedPaths V = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths W = new BreadthFirstDirectedPaths(digraph, w);

        int mindis = Integer.MAX_VALUE;
        int result = -1;


        for (int item : path) {
            if (V.hasPathTo(item) && W.hasPathTo(item)) {
                int dis = V.distTo(item) + W.distTo(item);
                if (dis < mindis) {
                    mindis = dis;
                    result = item;
                }
            }
        }
        return result;

    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
