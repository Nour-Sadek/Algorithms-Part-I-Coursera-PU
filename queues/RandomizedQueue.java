/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int tail = 0;
    private int amount = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.amount == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.amount;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("A null item can't be added to a randomized queue");
        }
        // Upsize array if full
        if (amount == this.queue.length) {
            this.resize(2 * this.queue.length);
        }
        queue[tail++] = item;
        this.amount++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.amount == 0) {
            throw new NoSuchElementException("The randomized queue is empty!");
        }
        // Choose random index
        int randomIndex = StdRandom.uniformInt(tail);
        Item removedItem = this.queue[randomIndex];
        // Remove item at tail
        Item atTail = this.queue[tail - 1];
        this.queue[--tail] = null;
        // Add item from tail to take place of removed item
        this.queue[randomIndex] = atTail;
        this.amount--;
        // Downsize array if only 1/4th full
        if (amount > 0 && amount == this.queue.length / 4) {
            this.resize(this.queue.length / 2);
        }
        return removedItem;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < this.tail; i++) {
            copy[i] = this.queue[i];
        }
        this.queue = copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.amount == 0) {
            throw new NoSuchElementException("The randomized queue is empty!");
        }
        return queue[StdRandom.uniformInt(this.tail)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Integer[] indices = new Integer[amount];
        private int curr = 0;

        {
            for (int i = 0; i < indices.length; i++) {
                indices[i] = i;
            }
            StdRandom.shuffle(indices);
        }

        public boolean hasNext() {
            return curr < amount;
        }

        public Item next() {
            if (curr >= amount) {
                throw new NoSuchElementException("No elements to iterate through!");
            }
            return queue[indices[curr++]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // Unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(6);
        StdOut.println(queue.dequeue());
        StdOut.println(queue.size());
        queue.enqueue(6);
        queue.enqueue(100);
        queue.enqueue(4);
        queue.enqueue(8);
        queue.dequeue();
        StdOut.println(queue.sample());
        if (!queue.isEmpty()) {
            Iterator<Integer> iter = queue.iterator();
            while (iter.hasNext()) {
                StdOut.println(iter.next());
            }
        }
    }

}
