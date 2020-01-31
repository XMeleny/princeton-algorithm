import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// By convention, the row and column indices are integers between 1 and n
public class Percolation {
    private boolean[][] graph;
    private final int size;
    private int openSites;
    private final WeightedQuickUnionUF uf;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n<=0) throw new IllegalArgumentException();

        graph=new boolean[n][n];
        openSites=0;
        size=n;
        uf=new WeightedQuickUnionUF(n*n+2);//add virtual node: n*n is for first row, n*n+1 is for last row
    }

    private int mapToArray(int tRow,int tCol){
        return tRow*size+tCol;
    }


    // opens the site (row, col) if it is not open already
    //1<=row,col<=n
    public void open(int row, int col){
        if (row<1||col<1||row>size||col>size) throw new IllegalArgumentException();

        if(!isOpen(row,col)){

            graph[row-1][col-1]=true;
            openSites++;

            int tRow=row-1;
            int tCol=col-1;
            int self=mapToArray(tRow,tCol);

            if (row==1)
                uf.union(self,size*size);
            if (row==size)
                uf.union(self,size*size+1);

            //if up is open, union up and self
            if (row>1&&isOpen(row-1,col)){
                uf.union((tRow-1)*size+tCol,self);
            }

            //if left is open, union left and self
            if (col>1&&isOpen(row,col-1)){
                uf.union(tRow*size+tCol-1,self);
            }

            //if right is open, union right and self
            if(col!=size&&isOpen(row,col+1)){
                uf.union(tRow*size+tCol+1,self);
            }

            //if bottom is open, union bottom and self
            if (row!=size&&isOpen(row+1,col)){
                uf.union((tRow+1)*size+tCol,self);
            }
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row<=0||col<=0||row>size||col>size) throw new IllegalArgumentException();
        return graph[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        // full: A full site is an open site that
        // can be connected to an open site in the top row
        // via a chain of neighboring (left, right, up, down) open sites.
        int self=mapToArray(row-1,col-1);
        if (isOpen(row,col)&&uf.find(self)==uf.find(size*size)){
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
        if (uf.find(size*size)==uf.find(size*size+1))
            return true;
        return false;
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation p=new Percolation(4);
        p.open(1,1);
        p.open(2,1);
        p.open(3,1);
        p.open(4,3);
//        System.out.println(p.isFull(1,1));
        System.out.println(p.percolates());
        p.open(3,2);
        System.out.println(p.percolates());

        p.open(3,3);
        System.out.println(p.percolates());


    }
}
