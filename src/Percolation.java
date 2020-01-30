import edu.princeton.cs.algs4.*;

// By convention, the row and column indices are integers between 1 and n
public class Percolation {
    private boolean[][] graph;
    private final int size;
    private int openSites;
    WeightedQuickUnionUF uf;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) throws IllegalArgumentException{
        if (n<=0) throw new IllegalArgumentException();

        graph=new boolean[n][n];
        openSites=0;
        size=n;
        uf=new WeightedQuickUnionUF(n*n);
    }


    // opens the site (row, col) if it is not open already
    //1<=row,col<=n
    public void open(int row, int col) throws IllegalArgumentException{
        if (row<1||col<1||row>size||col>size) throw new IllegalArgumentException();

        graph[row-1][col-1]=true;
        openSites++;

        //if up is open, union up and self
        if (isOpen(row-1,col)){
            uf.union((row-2)*size+(col-1),(row-1)*size+(col-1));
        }

        //if left is open, union left and self
        if (isOpen(row,col-1)){
            uf.union((row-1)*size+(col-2),(row-1)*size+(col-1));
        }

        //if right is open, union right and self
        if(isOpen(row,col+1)){
            uf.union((row-1)*size+col,(row-1)*size+(col-1));
        }

        //if bottom is open, union bottom and self
        if (isOpen(row+1,col)){
            uf.union(row*size+(col-1),(row-1)*size+(col-1));
        }
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
        if (uf.find((row-1)*size+(col-1))<size){
            return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSites;
    }

    // does the system percolate?
    public boolean percolates(){
        for (int i=0;i<size;i++){
            if (uf.find((size-1)*size+i)<size) return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args){
//        Percolation p=new Percolation(4);
        //todo: read input file and handle

    }
}
