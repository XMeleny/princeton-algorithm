import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private class Node{
        Item item;
        Node next;
    }
    Node head;
    Node tail;

    // construct an empty randomized queue
    public RandomizedQueue(){
        size=0;
        head=null;
        tail=null;
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

    // add the item
    public void enqueue(Item item){
        if(item==null) throw new IllegalArgumentException();
        //todo
        Node n=new Node();
        n.item=item;
        n.next=null;
        if(size==0){
            head=tail=n;
        }
        else{
            tail.next=n;
            tail=n;
        }
        size++;
    }

    // remove and return a random item
    public Item dequeue(){
        if(size==0) throw new NoSuchElementException();
        //todo
        size--;
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(size==0) throw new NoSuchElementException();

        //todo
        return null;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        //todo
        return null;
    }

    // unit testing (required)
    public static void main(String[] args){
        //todo
    }

}