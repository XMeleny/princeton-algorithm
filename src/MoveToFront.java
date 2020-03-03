import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.ArrayList;

public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        //init
        ArrayList<Character> list = new ArrayList<>();
        for (char c = 0; c < 256; c++) {
            list.add(c);
        }

        //handle
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();

            int index = list.indexOf(c);
            BinaryStdOut.write(index, 8);

            list.remove(index);
            list.add(0, c);
        }

        //close the stream
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        //init
        ArrayList<Character> list = new ArrayList<>();
        for (char c = 0; c < 256; c++) {
            list.add(c);
        }

        //handle
        while (!BinaryStdIn.isEmpty()) {
            int index = BinaryStdIn.readInt(8);
            char ch = list.get(index);
            BinaryStdOut.write(ch);

            list.remove(index);
            list.add(0, ch);
        }

        //close the stream
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
    }

}