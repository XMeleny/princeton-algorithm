import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.HashMap;

public class MoveToFront {

    private static class Node {
        char ch;
        Node prev;
        Node next;
    }


    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        HashMap<Character, Node> map = new HashMap<>();
        Node head = null;
        Node curr = null;
        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            //todo
            curr = null;
            if (map.containsKey(ch)) {
                curr = map.get(ch);
                if (curr != head) {
                    //it must have prev
                    curr.prev.next = curr.next;
                    if (curr.next != null)
                        curr.next.prev = curr.prev;

                    curr.next = head;
                    curr.prev = null;

                    head.prev = curr;
                    head = curr;
                }

            } else {
                if (head == null) {
                    head = new Node();
                    head.prev = null;
                    head.next = null;
                    head.ch = ch;
                } else {
                    curr = new Node();
                    curr.next = head;
                    curr.prev = null;
                    curr.ch = ch;

                    head.prev = curr;
                    head = curr;

                    map.put(ch, curr);
                }
            }
        }

        curr=head;
        while (curr!=null){
            BinaryStdOut.write(curr.ch);
            curr=curr.next;
        }

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