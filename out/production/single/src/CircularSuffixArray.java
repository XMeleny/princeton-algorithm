public class CircularSuffixArray {
    private final String string;

    // circular suffix array of s
    public CircularSuffixArray(String s){
        string=s;
    }

    // length of s
    public int length(){
        return string.length();
    }

    // returns index of ith sorted suffix
    public int index(int i){
        return 0;
    }

    // unit testing (required)
    public static void main(String[] args){

    }

}