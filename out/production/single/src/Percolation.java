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
        uf=new WeightedQuickUnionUF(n*n);
    }


    // opens the site (row, col) if it is not open already
    //1<=row,col<=n
    public void open(int row, int col){
        if (row<1||col<1||row>size||col>size) throw new IllegalArgumentException();

        if(!isOpen(row,col)){
            graph[row-1][col-1]=true;
            openSites++;

            //if up is open, union up and self
            if (row!=1&&isOpen(row-1,col)){
                uf.union((row-2)*size+(col-1),(row-1)*size+(col-1));
            }

            //if left is open, union left and self
            if (col!=1&&isOpen(row,col-1)){
                uf.union((row-1)*size+(col-2),(row-1)*size+(col-1));
            }

            //if right is open, union right and self
            if(col!=size&&isOpen(row,col+1)){
                uf.union((row-1)*size+col,(row-1)*size+(col-1));
            }

            //if bottom is open, union bottom and self
            if (row!=size&&isOpen(row+1,col)){
                uf.union(row*size+(col-1),(row-1)*size+(col-1));
            }

            for (int i=0;i<size;i++){
                for (int j=0;j<size;j++) {
                    if (graph[i][j])
                        System.out.print("O");
                    else
                        System.out.print("X");
                }
                System.out.println();
            }
            System.out.println();
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
        if (isOpen(row,col)&&uf.find((row-1)*size+(col-1))<size){
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
        Percolation p=new Percolation(4);
        p.open(1,1);
        p.open(2,1);
        p.open(3,1);
        p.open(4,3);
//        System.out.println(p.isFull(1,1));
        System.out.println(p.percolates());
        p.open(3,2);
        System.out.println(p.percolates());
        System.out.println(p.isFull(3,2));

        p.open(3,3);
        System.out.println(p.percolates());

        //todo: read input file and handle

    }
}
