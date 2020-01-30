import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int trails;
    private final double mean;
    private final double stddev;
    private final double arg=1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        //init
        this.trails=trials;
        double[] threshold = new double[trails];

        for (int i=0;i<trails;i++){
            Percolation p=new Percolation(n);
            while(!p.percolates()){
                int row=StdRandom.uniform(n)+1;
                int col=StdRandom.uniform(n)+1;
                p.open(row,col);
            }
            threshold[i]=(double) p.numberOfOpenSites()/(n*n);
        }

        this.mean=StdStats.mean(threshold);
        this.stddev=StdStats.stddev(threshold);
    }

    // sample mean of percolation threshold
    public double mean(){
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean-arg*stddev/Math.sqrt(trails);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean+arg*stddev/Math.sqrt(trails);
    }

    // test client (see below)
    public static void main(String[] args){
        //args: n and T
        int n=StdIn.readInt();
        int T= StdIn.readInt();
        PercolationStats p=new PercolationStats(n,T);
//        PercolationStats p=new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
    }
}
