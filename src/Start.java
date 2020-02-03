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

        int size=6;
        int count=0;
        for (int i=0;i<size-3;i++){
            for (int j=i+1;j<size-2;j++){
                for (int m=j+1;m<size-1;m++){
                    for (int n=m+1;n<size;n++){
                        count++;
                        System.out.println("i, j, m, n ="+i+"  "+j+"  "+m+"  "+n+"  ");
                    }
                }
            }
        }
        System.out.println(count);
    }
}
