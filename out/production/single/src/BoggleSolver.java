import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

import java.util.HashSet;

public class BoggleSolver {
    private final TST<Integer> tst;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null) throw new IllegalArgumentException();

        tst = new TST<>();

        for (String item : dictionary) {
            tst.put(item, scoreOf(item));
        }

    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null)
            throw new IllegalArgumentException();

        HashSet<String> result = new HashSet<>();
        for (int i = 0; i < board.rows(); i++)
            for (int j = 0; j < board.cols(); j++)
                check(board, new boolean[board.rows()][board.cols()], i, j, new StringBuilder(board.rows() * board.cols()), result);
        return result;
    }

    private void check(BoggleBoard board, boolean[][] marked, int nowRow, int nowCol, StringBuilder chars, HashSet<String> result) {

        if (marked[nowRow][nowCol]) {
            return;
        }

        char ch = board.getLetter(nowRow, nowCol);
        if (ch == 'Q')
            chars.append("QU");
        else
            chars.append(ch);

        String str = new String(chars);
//        System.out.println("checking: "+str);
        if (str.length() >= 3 && tst.contains(str)) {
            result.add(str);
        }

        //if not possible
        if (!tst.keysWithPrefix(str).iterator().hasNext()) {
            if (ch == 'Q')
                chars.delete(chars.length() - 2, chars.length());
            else
                chars.deleteCharAt(chars.length() - 1);
            return;
        }

        //if possible, mark now and go on
        marked[nowRow][nowCol] = true;


        //if up
        if (nowRow > 0) check(board, marked, nowRow - 1, nowCol, chars, result);
        //if down
        if (nowRow < board.rows() - 1) check(board, marked, nowRow + 1, nowCol, chars, result);

        //if left
        if (nowCol > 0) check(board, marked, nowRow, nowCol - 1, chars, result);
        //if right
        if (nowCol < board.cols() - 1) check(board, marked, nowRow, nowCol + 1, chars, result);

        //if up left
        if (nowRow > 0 && nowCol > 0) check(board, marked, nowRow - 1, nowCol - 1, chars, result);
        //if up right
        if (nowRow > 0 && nowCol < board.cols() - 1) check(board, marked, nowRow - 1, nowCol + 1, chars, result);

        //if down left
        if (nowRow < board.rows() - 1 && nowCol > 0) check(board, marked, nowRow + 1, nowCol - 1, chars, result);

        //if down right
        if (nowRow < board.rows() - 1 && nowCol < 3) check(board, marked, nowRow + 1, nowCol + 1, chars, result);


        marked[nowRow][nowCol] = false;
        if (ch == 'Q')
            chars.delete(chars.length() - 2, chars.length());
        else
            chars.deleteCharAt(chars.length() - 1);


    }


    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null) throw new IllegalArgumentException();

        int size = word.length();

        if (!tst.contains(word) || size <= 2) return 0;
        if (size == 3 || size == 4) return 1;
        if (size == 5) return 2;
        if (size == 6) return 3;
        if (size == 7) return 5;
        else return 11;
    }

    public static void main(String[] args) {
        In in = new In("dictionary-algs4.txt");
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard("board4x4.txt");
        Iterable<String> iterable=solver.getAllValidWords(board);
        int count=0;
        int score = 0;
        for (String word : iterable) {
            StdOut.println(word);
            score += solver.scoreOf(word);
            count++;
        }
        StdOut.println("Score = " + score);
        StdOut.println("count = " + count);
    }
}
