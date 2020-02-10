import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;

public class Solver {
    private class Node implements Comparable<Node> {
        Board board;
        int moves;
        int priority;
        Node previous;


        Node(Board board, int moves, Node previous) {
            this.board = board;
            this.moves = moves;
            this.priority = moves + board.manhattan();
            this.previous = previous;
        }

        @Override
        public int compareTo(Node that) {
            if(this.priority<that.priority) return -1;
            if(this.priority==that.priority) return 0;
            return 1;
        }
    }

    private boolean success;
    private Node solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        success = false;
        solution = null;

        MinPQ<Node> openList = new MinPQ<>();

        openList.insert(new Node(initial, 0, null));

        Node current;
        while (!openList.isEmpty()) {
            current = openList.delMin();
            if (current.board.isGoal()) {
                success = true;
                solution = current;
                break;
            }
            if(current.board.twin().isGoal()){
                break;
            }
            for (Board board : current.board.neighbors()) {
                if (!isInCloseList(current,board)) openList.insert(new Node(board, current.moves + 1, current));
            }
        }
    }

    private boolean isInCloseList(Node current,Board board){
        Node node=current.previous;
        while(node!=null){
            if(board.equals(node.board)) return true;
            node=node.previous;
        }
        return false;
    }


    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return success;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (success) {
            return solution.moves;
        }
        return -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (success) {
            ArrayList<Board> result = new ArrayList<>();
            Node current = solution;
            while (current != null) {
                result.add(0, current.board);
                current = current.previous;
            }
            return result;
        }
        return null;
    }

    // test client (see below)
    public static void main(String[] args) {

    }

}