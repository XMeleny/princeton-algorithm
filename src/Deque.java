import edu.princeton.cs.algs4.StdOut;

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
        i=i%n;
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
        Item[] result=(Item[]) new Object[capacity];
        int temp;
        for (int i=0;i<size;i++){
            temp=first+i;
            temp=mod(i,capacity);
            result[i]=array[temp];
        }
        array=result;
    }
    private void shrinkArray(){
        capacity=capacity/2;
        //todo: copy and paste
        Item[] result=(Item[]) new Object[capacity];
        int temp;
        for (int i=0;i<size;i++){
            temp=first+i;
            temp=mod(i,capacity);
            result[i]=array[temp];
        }
        array=result;
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

        size--;
        if (size<capacity/4) shrinkArray();

        Item result=array[first];
        array[first]=null;//problem: would it affect result?
        first++;
        first=mod(first,capacity);
        return result;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(size==0) throw new NoSuchElementException();

        size--;
        if (size<capacity/4) shrinkArray();

        Item result=array[last];
        array[last]=null;//problem: would it affect result?
        last--;
        last=mod(last,capacity);
        return result;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new Iterator<Item>() {
            private int current=first;
            @Override
            public boolean hasNext() {
                return array[current] != null;
            }

            @Override
            public Item next() {
                Item result=array[current];
                if (result==null) throw new NoSuchElementException();
                current++;
                current=mod(current,capacity);
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args){

    }

}

