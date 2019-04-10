/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int MIN_ARRAY_SIZE = 2;
    private int n = 0;
    private Item[] rdmQue;

    
    public RandomizedQueue() {                 // construct an empty randomized queue
        rdmQue = (Item[]) new Object[MIN_ARRAY_SIZE];
    }

    public boolean isEmpty() {                 // check is the RandomizedQueue empty
        return n == 0;
    }

    public int size() {                         // return the size of the RandomizedQueue
        return n;
    }

    public void enqueue(Item item) {            // add element to the RandomizedQueue
        if (item == null) throw new IllegalArgumentException();
        if (n == rdmQue.length) resize(2 * rdmQue.length);
        rdmQue[n++] = item;
    }

    private void resize(int length) {
        Item[] copy = (Item[]) new Object[length];
        for (int i = 0; i < n; i++)
            copy[i] = rdmQue[i];
        rdmQue = copy;
    }

    public Item dequeue() {                       // retrieve and remove a random element in the RandomizedQueue
        if (isEmpty()) throw new NoSuchElementException();
        int removeIndex = StdRandom.uniform(n);
        Item returned = rdmQue[removeIndex];
        rdmQue[removeIndex] = rdmQue[--n];
        rdmQue[n] = null;

        if (n > 0 && n == rdmQue.length / 4) resize(rdmQue.length / 2);
        return returned;
    }

    public Item sample() {                      // retrieve a random element in the RandomizedQueue
        if (isEmpty()) throw new NoSuchElementException();
        int removeIndex = StdRandom.uniform(n);
        return rdmQue[removeIndex];

    }

    public Iterator<Item> iterator() {
        return new RdmQueIter();
    }

    private class RdmQueIter implements Iterator<Item> {
        private int size;
        private Item[] items;

        public RdmQueIter() {
            items = rdmQue;
            StdRandom.shuffle(items);
            size = n;
        }

        public boolean hasNext() { return size > 0;
        }
        
        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[--size];
        }
    }


    public static void main(String[] args) {
    }
}
