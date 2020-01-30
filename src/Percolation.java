import edu.princeton.cs.algs4.*;

public class Percolation {
    //warning: the input row and col is in the range of [1,n]
    private boolean[][] graph;
    private int[][] connectTo;
    private final int size;
    WeightedQuickUnionUF uf;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) throws IllegalArgumentException{
        if (n<=0) throw new IllegalArgumentException();

        this.graph=new boolean[n][n];
        this.connectTo=new int[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                connectTo[i][j]=i*n+j;
//                System.out.println("connectTo(i,j)= "+connectTo[i][j]);
//                System.out.println("graph(i,j)= "+graph[i][j]);
            }
        }
        this.size=n;
        uf=new WeightedQuickUnionUF(n*n);
    }


    // opens the site (row, col) if it is not open already
    //1<=row,col<=n
    public void open(int row, int col) throws IllegalArgumentException{
        if (row<1||col<1||row>size||col>size) throw new IllegalArgumentException();
        graph[row-1][col-1]=true;

        //self:row-1,col-1
        //up: row-2,col-1
        //bottom:row,col-1
        //left: row-1,col-2
        //right:row-1,col

        //if up, union up and self


    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) throws IllegalArgumentException{
        if (row<=0||col<=0||row>size||col>size) throw new IllegalArgumentException();
        return graph[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        // full: A full site is an open site that
        // can be connected to an open site in the top row
        // via a chain of neighboring (left, right, up, down) open sites.
        int i=row-1,j=col-1;
        while(connectTo[i][j]!=i*size+j){
            i=connectTo[i][j]/size;
            j=connectTo[i][j]%size;
        }
        if (i==0)
            return true;
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        //input? get from graph?
        int count=0;
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if (isOpen(i,j)){
                    //warning: should i+1, j+1?
                    count++;
                }
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates(){
        for (int i=0;i<size;i++){
            if (findRoot(size-1,i)<size) return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args){
//        Percolation p=new Percolation(4);
        //todo: read input file and handle

    }
}
