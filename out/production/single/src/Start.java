public class Start {
    private static int mod(int i,int n){
        i=i%n;
        if (i<=0){
            return n+i;
        }
        else{
            return i%n;
        }
    }
    /**
     * this is a program to get started
     * @param args just normal arguments
     */
    public static void main(String[] args) {
        System.out.println("hello world");

        //输出从命令行中获取的参数
//        for (String s:args)
//            System.out.println(s);

        int i;
        i=mod(-5,4);
        System.out.println(i);
    }
}
