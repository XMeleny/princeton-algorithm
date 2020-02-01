import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){
        int k= StdIn.readInt();
        Deque<String> deque=new Deque<>();
//        RandomizedQueue<String> randomizedQueue=new RandomizedQueue<>();

        while(!StdIn.isEmpty()){
            String item=StdIn.readString();
            deque.addFirst(item);
//            randomizedQueue.enqueue(item);
        }

        int count=0;
        for (String s:deque){
            StdOut.println(s);
            count++;
            if(count>=k) break;
        }
//        count=0;
//        for (String s:randomizedQueue){
//            StdOut.println(s);
//            count++;
//            if(count>=k) break;
//        }
    }

}
