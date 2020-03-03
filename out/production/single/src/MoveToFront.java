import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        //init
        char[] seq = new char[256];
        for (char i = 0; i < 256; i++) {
            seq[i] = i;

//            System.out.println(seq[i]);
        }


        //read and print and move
        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            System.out.println("the ch is: " + ch);
            //find the position of ch
            char position = 0;
            if (seq[ch] == ch) {
                position = ch;
            } else {
                for (char i = 0; i < 256; i++) {
                    if (seq[i] == ch) {
                        position = i;
                        break;
                    }
                }
            }
//            BinaryStdOut.write(position);
            System.out.println("position = " + Integer.toHexString(position));


            //move to the front; bubble up
            if (position != 0) {
                while (position != 0) {
                    swapChar(seq, position, position - 1);
                    position--;
                }
            }
        }

    }

    private static void swapChar(char[] seq, char pos1, int pos2) {
        char temp = seq[pos1];
        seq[pos1] = seq[pos2];
        seq[pos2] = temp;
    }


    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {

    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
    }

}