import java.util.ArrayList;

public class ProductOfNumbers {
    ArrayList<Integer> list;

    public ProductOfNumbers() {
        list = new ArrayList<>();
    }

    public void add(int num) {
        list.add( num);
    }

    public int getProduct(int k) {
        int result=1;
        for(int i = list.size()-1; i>=list.size()-k; i--){
            result*=list.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        ProductOfNumbers p = new ProductOfNumbers();
        p.add(3);
        p.add(0);
        p.add(2);
        p.add(5);
        p.add(4);
        System.out.println(p.getProduct(2));
        System.out.println(p.getProduct(3));
        System.out.println(p.getProduct(4));
        p.add(8);
        System.out.println(p.getProduct(2));
    }
}
