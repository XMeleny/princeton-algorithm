import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String string = BinaryStdIn.readString();
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(string);

//        String string = new String(builder);
//        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(string);
        System.out.println(string);

        for (int i = 0; i < circularSuffixArray.length(); i++) {
            if (circularSuffixArray.index(i) == 0) {
                BinaryStdOut.write(i, 8);
//                System.out.println(i);
                break;
            }
        }

        for (int i = 0; i < circularSuffixArray.length(); i++) {
            BinaryStdOut.write(string.charAt((circularSuffixArray.index(i) + 1) % string.length()));
//            System.out.println(string.charAt(circularSuffixArray.index(i) + 1) % string.length());
        }

        BinaryStdOut.close();

    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
//        while (!BinaryStdIn.isEmpty()) {
//
//        }
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        if (args[0].equals("+")) inverseTransform();
    }

}