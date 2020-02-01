import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

//deque: 双端队列
public class Deque<Item> implements Iterable<Item> {
    private class Node{
        private Item item;
        private Node next;
        private Node past;
    }

    private Node first;
    private Node last;

    private int size;


    // construct an empty deque
    public Deque(){
        first=null;
        last=null;
        size=0;
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

    // add the item to the front
    public void addFirst(Item item){
        if (item==null) throw new IllegalArgumentException();

        Node n=new Node();
        n.item=item;
        n.next=null;
        n.past=null;

        if(size==0){
            first=last=n;
        }
        else{
            n.next=first;
            first.past=n;
            first=n;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item==null) throw new IllegalArgumentException();

        Node n=new Node();
        n.item=item;
        n.next=null;
        n.past=null;

        if (size==0){
            first=last=n;
        }
        else{
            last.next=n;
            n.past=last;
            last=n;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(size==0) throw new NoSuchElementException();

        Item result=first.item;

        if (size==1){
            first=last=null;
        }
        else{
            Node temp=first;
            first=first.next;
            first.past=null;
            temp.next=null;
        }
        size--;

        return result;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(size==0) throw new NoSuchElementException();

        Item result=last.item;
        if (size==1){
            first=last=null;
        }
        else {
            Node temp=last;
            last=last.past;
            last.next=null;
            temp.past=null;
        }
        size--;

        return result;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new Iterator<Item>() {
            private Node current=first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if(current==null) throw new NoSuchElementException();
                Item result=current.item;
                current=current.next;
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

