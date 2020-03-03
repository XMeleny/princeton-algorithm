import java.util.*;

public class CircularSuffixArray {
    private String input;
    private Integer[] index;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException();

        int len = s.length();
        index = new Integer[len];

        // init
        for (int i = 0; i < len; i++) index[i] = i;

        // sort
        Arrays.sort(index, new Comparator<Integer>() {
            @Override
            public int compare(Integer index1, Integer index2) {
                // originalArray[index] start from index
                for (int i = 0; i < len; i++) {
                    if (index1 >= len) index1 = 0;
                    if (index2 >= len) index2 = 0;

                    if (s.charAt(index1) > s.charAt(index2)) return 1;
                    if (s.charAt(index1) < s.charAt(index2)) return -1;

                    index1++;
                    index2++;
                }
                return 0;
            }
        });
    }

    // length of s
    public int length() {
        return index.length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= length()) throw new IllegalArgumentException();
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray circularSuffixArray = new CircularSuffixArray("ABRACADABRA!");
        for (int i = 0; i < circularSuffixArray.length(); i++) {
            System.out.println(circularSuffixArray.index(i));
        }
    }

}