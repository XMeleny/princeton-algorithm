public class Start {
    private static int mod(int i,int n){
        i=i%n;
        if (i<0){
            return n+i;
        }

        else{
            return i;
        }
    }
    /**
     * this is a program to get started
     * @param args just normal arguments
     */
    public static void main(String[] args) {

        int[] a=new int[5];
        System.out.println(a.length);
    }
}
