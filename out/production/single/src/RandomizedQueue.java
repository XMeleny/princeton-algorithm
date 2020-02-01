import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private int capacity;
    private int last;

    private Item[] array;

    // construct an empty randomized queue
    public RandomizedQueue(){
        size=0;
        capacity=1;
        last=0;
        array=(Item[]) new Object[capacity];
    }

    private void enlargeArray(){
        capacity=2*capacity;
        Item[] result=(Item[]) new Object[capacity];
        for (int i=0;i<size-1;i++){
            result[i]=array[i];
        }
        array=result;
    }
    private void shrinkArray(){
        capacity=capacity/2;
        Item[] result=(Item[]) new Object[capacity];
        for (int i=0;i<size;i++){
            result[i]=array[i];
        }
        array=result;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        if(size==0){
            return true;
        }
        else return false;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    //enqueue: last++, dequeue: last--(because it's not fifo)
    // add the item
    public void enqueue(Item item){
        if(item==null) throw new IllegalArgumentException();

        size++;
        if(size>capacity) enlargeArray();
        if(size==1){
            array[last]=item;
        }
        else{
            last++;
            array[last]=item;
        }

    }

    // remove and return a random item
    public Item dequeue(){
        if(size==0) throw new NoSuchElementException();

        size--;

        Item result;
        if (size==0) {
            result=array[last];
            array[last]=null;
        }
        else {
            int position=StdRandom.uniform(size);
            result=array[position];
            array[position]=array[last];
            array[last]=null;
            last--;
        }

        if(size<capacity/4) shrinkArray();

        return result;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(size==0) throw new NoSuchElementException();
        return array[StdRandom.uniform(size)];
    }


    private class TheIterator implements Iterator<Item>{
        private int current;
        private Item[] result;
        private TheIterator(){
            current=0;

            result= (Item[]) new Object[size];
            for (int i=0;i<size;i++){
                result[i]=array[i];
            }
            StdRandom.shuffle(result);
        }
        @Override
        public boolean hasNext() {
            if (current<size) return true;
            return false;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item item=result[current];
            current++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new TheIterator();
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> randomizedQueue=new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        randomizedQueue.enqueue(5);
        randomizedQueue.enqueue(6);

        for (Integer integer:randomizedQueue){
            System.out.println(integer);
        }
//
//        System.out.println();
//        for (Integer integer:randomizedQueue){
//            System.out.println(integer);
//        }
//
//        System.out.println();
//        for (Integer integer:randomizedQueue){
//            System.out.println(integer);
//        }



    }

}