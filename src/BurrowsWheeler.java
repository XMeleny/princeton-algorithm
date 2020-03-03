import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String string = BinaryStdIn.readString();
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(string);

        int len = circularSuffixArray.length();

        for (int i = 0; i < len; i++) {
            if (circularSuffixArray.index(i) == 0) {
                //write integer, using 4B
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i = 0; i < len; i++) {
            int position = circularSuffixArray.index(i) - 1;
            if (position < 0) position = len - 1;

            BinaryStdOut.write(string.charAt(position));
        }

        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        //get all information
        int first = BinaryStdIn.readInt();
        String input = BinaryStdIn.readString();
        char[] t = input.toCharArray();

        //new some data structure
        int len = input.length();
        int[] next = new int[len];

        HashMap<Character, Queue<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(t[i])) {
                map.get(t[i]).offer(i);
            } else {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                map.put(t[i], queue);
            }
        }

        //construct the next[]
        Arrays.sort(t);
        for (int i = 0; i < len; i++) {
            next[i] = map.get(t[i]).poll();
        }

        //follow the next[] to rebuild the string and write it
        for (int i = 0; i < len; i++) {
            BinaryStdOut.write(t[first]);
            first = next[first];
        }

        BinaryStdOut.close();

    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        if (args[0].equals("+")) inverseTransform();
    }

}