import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    //warning: the input row and col is in the range of [1,n]
    private boolean[][] graph;
    private int[][] connectTo;
    private final int size;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n<=0) throw new IllegalArgumentException();

        //todo: think if needed virtual node at top and bottom
        this.graph=new boolean[n][n];
        this.connectTo=new int[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                connectTo[i][j]=i*n+j;
                System.out.println("connectTo(i,j)= "+connectTo[i][j]);
                System.out.println("graph(i,j)= "+graph[i][j]);
            }
        }
        this.size=n;


    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) throws IllegalArgumentException{
        if (row<=0||col<=0||row>size||col>=size) throw new IllegalArgumentException();
        graph[row-1][col-1]=true;
        //todo: deal with the connected component
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) throws IllegalArgumentException{
        if (row<=0||col<=0||row>size||col>size) throw new IllegalArgumentException();
        return graph[row-1][col-1];
    }

//    // is the site (row, col) full?
//    // what does full mean?
//    public boolean isFull(int row, int col)
//
//    // returns the number of open sites
//    public int numberOfOpenSites()
//
//    // does the system percolate?
//    public boolean percolates()

    // test client (optional)
    public static void main(String[] args){
        Percolation p=new Percolation(4);

    }
}
