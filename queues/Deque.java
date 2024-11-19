/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node before;
    }

    // construct an empty deque
    public Deque() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("A null node cannot be added");
        }
        if (this.size == 0) {
            this.first = new Node();
            this.first.item = item;
            this.last = this.first;
        }
        else {
            Node currFirst = this.first;
            this.first = new Node();
            this.first.item = item;
            this.first.next = currFirst;
            currFirst.before = this.first;
        }
        this.size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("A null node cannot be added");
        }
        if (this.size == 0) {
            this.last = new Node();
            this.last.item = item;
            this.first = this.last;
        }
        else {
            Node currLast = this.last;
            this.last = new Node();
            this.last.item = item;
            this.last.before = currLast;
            currLast.next = this.last;
        }
        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The deque is empty!");
        }
        Node currFirst = this.first;
        if (this.size == 1) {
            this.first = null;
            this.last = null;
        }
        else {
            this.first = this.first.next;
            this.first.before = null;
        }
        this.size--;
        return currFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The deque is empty!");
        }
        Node currLast = this.last;
        if (this.size == 1) {
            this.first = null;
            this.last = null;
        }
        else {
            this.last = currLast.before;
            this.last.next = null;
        }
        this.size--;
        return currLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The Deque is empty!");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // Unit testing
    public static void main(String[] args) {

        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(90);
        StdOut.println(deque.size());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.size());
        deque.addLast(100);
        deque.addFirst(-1);
        StdOut.println(deque.size());
        StdOut.println(deque.removeFirst());
        deque.addFirst(10);
        deque.addLast(110);
        deque.addFirst(1);

        if (!deque.isEmpty()) {
            Iterator<Integer> iter = deque.iterator();
            while (iter.hasNext()) {
                StdOut.println(iter.next());
            }
        }

    }

}
