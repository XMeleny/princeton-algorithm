import java.util.Iterator;
import java.util.NoSuchElementException;

//deque: 双端队列
public class Deque<Item> implements Iterable<Item> {

    private int first;
    private int last;
    private int capacity;
    private int size;
    private Item[] array;


    //return i%n

    private int mod(int i,int n){
        if (i<=0){
            return n+i;
        }
        else{
            return i%n;
        }
    }

    // construct an empty deque
    public Deque(){
        first=0;
        last =0;
        size=0;
        capacity=1;
        array=(Item[])new Object[capacity];
    }

    // is the deque empty?
    public boolean isEmpty(){
        if (size==0) return true;
        return false;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    private void enlargeArray(){
        capacity=2*capacity;

    }

    // add the item to the front
    public void addFirst(Item item){
        if (item==null) throw new IllegalArgumentException();

        if(size==0){
            size++;
            array[first]=item;
        }
        else {
            size++;
            if (size>capacity) enlargeArray();
            first--;
            first=mod(first,capacity);
            array[first]=item;
        }
    }

    // add the item to the back
    public void addLast(Item item){
        if (item==null) throw new IllegalArgumentException();
        //todo
        if(size==0){
            size++;
            array[last]=item;
        }
        else{
            size++;
            if (size>capacity) enlargeArray();
            last++;
            last=mod(last,capacity);
            array[last]=item;
        }
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(size==0) throw new NoSuchElementException();
        //todo
        return null;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(size==0) throw new NoSuchElementException();
        //todo
        return null;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        //todo
        return null;
//        return new Iterator<Item>() {
//            @Override
//            public boolean hasNext() {
//                return false;
//            }
//
//            @Override
//            public Item next() {
//                return null;
//            }
//        };
    }

    // unit testing (required)
    public static void main(String[] args){
        int i=-1%100;
        System.out.println(i);
    }

}

