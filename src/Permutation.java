import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){
        int k=Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue=new RandomizedQueue<>();

        while(!StdIn.isEmpty()){
            String item=StdIn.readString();
            randomizedQueue.enqueue(item);
        }

        int count=0;
        for (String s:randomizedQueue){
            count++;
            if(count>k) break;
            StdOut.println(s);

        }
    }

}
